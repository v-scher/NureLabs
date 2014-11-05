package ua.nure.shcherbatenko.mzup.coursework;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

public class Work implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected long duration;
	private ArrayList<Work> subWorks;
	ArrayList<Work> preds; // роботи, що передують даній
	private Work parent;
	public TreeMap<String,Integer> res;
	private TreeMap<String,Integer> resPrices;
	private PrintStream out;
	
	public void setPrintStream(PrintStream Path) {
		out = Path;
	}
	
	private PrintStream out() {
		Work pr = this;
		while (pr.parent != null)
			pr = pr.parent;
		return (pr.out == null) ? System.out : pr.out;
	}
	
	Work(String name){
		this.name = name;
		this.duration = 0;
		this.parent = null;
		this.res = null;
		this.resPrices = null;
		subWorks = null;
		out = null;
	}
	
	Work(String name, long duration, Work parent){
		this.name = name;
		this.duration = duration;
		this.parent = parent;
		this.res = null;
		this.resPrices = null;
		subWorks = null;
	}

	private boolean ableToPred(Work pred) {
		return ( ! pred.alreadyHavePred(this));
	}
	
	public Work addRes(String name){
		return addRes(name, 100);
	}
	
	public Work addRes(String name, int PercentageOfEmployment){
		// Якщо це ПРОСТА робота
		if(subWorks == null && duration != 0){ 
			// виконуємо перевірку коректності введення відсотка зайнятості
			if (PercentageOfEmployment > 100) {
				out().println("Зайнятість ресурсу \"" + name + "\" зменшено до 100%");
				// виправляємо на коректне значення
				PercentageOfEmployment = 100;
			}
			// нормуємо ім'я ресурсу
			name = name.toLowerCase();
			
			// Якщо ресурсів ще не призначено (поточний - перший ресурс)
			if(res == null ){ 
				// Створюємо контейнерний масив для ресурсів
				res = new TreeMap<String, Integer>();
			} else {
				// Інакше перевіряємо чи не повторюється ресурс
				if (res.get(name) != null)
					out().println("Ресурс \"" + name + "\" перезаписано");
			}
			// виконуємо запис
			res.put(name, PercentageOfEmployment);
		} else {
			out().println("\"" + name + "\" до роботи \"" + this.name + "\" не додано - складним роботам ресурси призначаються автоматично");
		}
		return this;
	}
	
	public Work addWork(String workName){
		return this.addWork(workName, 0, null);
	}

	public Work addWork(String workName, long hours) {
		return this.addWork(workName, hours, null);
	}
	
	public Work addWork(String workName, long hours, Work predecessor){
		if (hours < 0){
			out().println("Тривалість роботи \"" + workName + "\" має бути додатньою");
			return null;
		}
		
		Object tmpArrPreds[] = null;
		// Якщо підробіт ще не призначено
		if (subWorks == null) {
			subWorks = new ArrayList<Work>();
			if (this.preds != null) {
				tmpArrPreds = this.preds.toArray();
				out().println("Попередників роботи \"" + this.name + "\" втрачено, призначено роботу \"" + workName + "\"");
				this.preds = null;
			}
				
			if (this.res != null) {
				out().println("Ресурси \"" + this.name + "\" втрачено, призначено роботу \"" + workName + "\"");
				res = null;
				this.duration = 0;
			}
		}
		
		Work tmp;
		if (getWork(workName) == null) {
			if (predecessor != null 
			&& predecessor.parent.equals(this))
				subWorks.add(predecessor.parent.subWorks.indexOf(predecessor) + 1, tmp = new Work(workName, hours*3600000, this));
			else
				subWorks.add(tmp = new Work(workName, hours*3600000, this));
			if (tmpArrPreds != null)
				for (Object P : tmpArrPreds)
					tmp.addPred((Work)P);
			
			return tmp;
		} else {
			out().println("Роботу \"" + workName +"\" вже призначено.");
			return null;
		}
	}

	public Work addPred(Work W) {
		// Якщо робота СКЛАДНА
		if (this.subWorks != null) {
			for (Work I : this.subWorks)
				I.addPred(W);
		} else {
			// функція лише для ПІДРОБІТ проекту
			if (this.parent == null) {
				out().println("Попередників можуть мати лише підроботи проекту");
				return null;
			}
			if (this.equals(W)) {
				out().println("Робота " + W.name + " не може бути власним попередником");
				return null;
			}
				
			if ( ! this.ableToPred(W)) {
				out().println("ЦИКЛ! Робота \"" + W.name + "\" не може бути попередником \"" + this.name + "\"");
				return null;
			}
		
			if (this.preds == null)
				this.preds = new ArrayList<Work>();		
				
			if (this.preds.contains(W)) {
				out().println("Роботу попередник \"" + W.name + "\" вже призначено роботі \"" + this.name + "\"");
				return null;
			}
				
			this.preds.add(W);
		}
		return this;
	}

	private boolean alreadyHavePred(Work pred) {
		if (this.preds != null) {
			if (this.preds.contains(pred))
				return true;
			else
				for (Work W : this.preds)
					if (W.alreadyHavePred(pred))
						return true;
		}
		return false;
	}
	
	public void alignResources() {
		TreeMap<Work, ArrayList<Work>> workPreds= new TreeMap<Work, ArrayList<Work>>(new Comparator<Work> (){
			@Override
			public int compare(Work o1, Work o2) {
				return o1.name.compareTo(o2.name);
			}
		});
		for (Work W : elementaryWorkArray())
			workPreds.put(W, W.preds);
		
		final Work project = this;
		// сортуємо ресурси за навантаженістю протягом проекту
		ArrayList<String> resources =  new ArrayList<String>(this.getResources().keySet());
		Comparator<String> loadingSort = new Comparator<String>() {
			@Override
			public int compare(String res0, String res1) {
				TreeMap<Long, ResourceOverload> OLI0 = project.getOverloadedIntervals(res0);
				TreeMap<Long, ResourceOverload> OLI1 = project.getOverloadedIntervals(res1);
				
				int oli0 = 0;
				int oli1 = 0;
				if (OLI0 != null)
					for (Long L : OLI0.keySet())
						oli0 += OLI0.get(L).overload;
				if (OLI1 != null)
					for (Long L : OLI1.keySet())
						oli1 += OLI1.get(L).overload;
				return oli1 - oli0;
			}
		};
		Collections.sort(resources, loadingSort);
		
		// вирівнюємо ресурси починаючи з найбільш завантажених
		boolean changed = true;
		while (changed) {
			changed = false;
			int changedResources = 1;
			while (changedResources != 0) {
				resources =  new ArrayList<String>(this.getResources().keySet());
				Collections.sort(resources, loadingSort);
				changedResources = 0;
				for (String S : resources) {
					if (this.alignResource(S, workPreds))
						changedResources++;
				}
			}
		}
	}
	
	public boolean alignResource(final String resArg, TreeMap<Work, ArrayList<Work>> workPreds) {
		// перевіряємо ресурс на перевентаження
		TreeMap<Long, ResourceOverload> intervals;
		// перед вирівнюванням складаємо перелік оригінальних попередників
		boolean changed = false;
		while ( (intervals =  this.getOverloadedIntervals(resArg)) != null) {
			//---------------------------------------------------------------------------------------------------------------------
			this.showGuntt();
			out().println(resArg);
			Long L = intervals.firstKey();
			out().println(L/3600000 + " год, перевантаження " + intervals.get(L).overload);
			for (Work W : intervals.get(L).works)
				out().println(W + " " + W.res.get(resArg));
			out().println("                                    --//--//--//--//--//--Виконуємо вирівнювання--//--//--//--//--//--");
			//---------------------------------------------------------------------------------------------------------------------
			final TreeMap<Work, Long> graphic = this.getGraphic();
			ArrayList<Work> problemWorks = intervals.firstEntry().getValue().works;
			
			ArrayList<Work> crWorks = new ArrayList<Work>();
			ArrayList<Work> notCrWorks = new ArrayList<Work>();
			int crLoad = 0;
			
			// ПЕРЕВІРЯЄМО роботи, що використовують ресурс на критичність
			for (Work W : problemWorks) {
				if (W.isCritical()) {
					// і визначаємо частку завнатаження критичних
					crLoad += W.res.get(resArg);
					crWorks.add(W);
				} else {
					// і некритичних робіт
					notCrWorks.add(W);
				}
			}
			
			// Формуємо масив робіт для переносу
			int loading = 0;
			ArrayList<Work> unCompatibleWorks = new ArrayList<Work>();
			// Для початку заносимо туди ВСІ унікальні роботи
			for (Work W : problemWorks) {
				unCompatibleWorks.add(W);
			}
			
			// пріоритет мають найтриваліші роботи для виконання паралельно (їх не буде перенесено)
			Collections.sort(notCrWorks, new Comparator<Work>() {
				@Override
				public int compare(Work arg0, Work arg1) {/*
					if (arg1.duration == arg0.duration)
						return (int) (graphic.get(arg0) + arg0.duration - graphic.get(arg1) - arg1.duration);
					else
						return (int) (arg1.duration - arg0.duration);*/
					//return (int) (graphic.get(arg0) + arg0.duration - graphic.get(arg1) - arg1.duration);
					//return (int) (graphic.get(arg0) - graphic.get(arg1));
					return (int) (arg0.freeReserve() - arg1.freeReserve() +graphic.get(arg0) - arg0.duration - graphic.get(arg1) + arg1.duration);
				}
			});
			
			// Якщо треба пренести ДЕЯКІ некритичні роботи
			if (crLoad <= 100) {
				// Видаляємо з переліку робіт для переносу всі критичні
				for (Work W : crWorks) {
					// одразу визначаємо яку частку ресурсу вони займають
					loading += W.res.get(resArg);
					// переносимо всі, окрім критичних
					unCompatibleWorks.remove(W);
				}
			} else {
				// пріоритет на ПЕРШОЧЕРГОВЕ ВИКОНАННЯ у найбільш завантажених робіт
				Collections.sort(crWorks, new Comparator<Work>() {
					@Override
					public int compare(Work arg0, Work arg1) {
						long endOfWork = 0;
						// залишаємо ті, що закінчуються раніше
						if ( (endOfWork = (graphic.get(arg0) + arg0.duration - graphic.get(arg1) - arg1.duration)) != 0 )
							return (int) endOfWork;
						// залишаємо триваліші
						if (arg1.duration != arg0.duration)
							return (int) (arg1.duration - arg0.duration);
						// залишаємо найбільш завантажені
						return (int) (arg1.res.get(resArg) - arg0.res.get(resArg));
					}
				});
				
				for (Work W : crWorks) {
					if ( crLoad > 100 ) {
						// одразу визначаємо яку частку ресурсу вони займають
						crLoad -= W.res.get(resArg);
						// залишаємо на перенос всі, окрім найбільш завантажених
						unCompatibleWorks.remove(W);
					} else
						break;
				} 
			}
			
			// перевіряємо НЕкритичні роботи по одній, чи можна їх виконати паралельно з критичними
			for (Work W : notCrWorks) {
				// Якщо можна, то 
				if ((W.res.get(resArg) + loading) <= 100) {
					// Додаємо завантаження ресурсу
					loading += W.res.get(resArg);
					// і видаляємо зі списку несумісних робіт
					unCompatibleWorks.remove(W);
				}
			}
			
			// Знаходимо серед сумісних роботу, яка закінчується раніше за решту
			Work shortestWork1 = null;
			long shortestStart = 0;
			
			// Тепер перевіряємо всі поточні роботи
			for (int i = 0; i < problemWorks.size(); i++) {
				if ( ! unCompatibleWorks.contains(problemWorks.get(i))) {
					// шукаємо поточну роботу в графіку
					Work current1;
					long currentStart = graphic.get( current1 = problemWorks.get(i) );
					
					if (shortestWork1 == null
					|| (shortestStart + shortestWork1.duration) > (currentStart + current1.duration)) {
						shortestWork1 = current1;
						shortestStart = currentStart;
					}
				}
			}
			
			// ПЕРЕПРИЗНАЧАЄМО РОБОТИ
			for (Work workToChange : unCompatibleWorks) {
				out().println("----- Роботі " + workToChange + " призначаємо попередника " + shortestWork1);
				workToChange.preds = null;
				for (Work W : workPreds.get(workToChange)) {
					workToChange.addPred(W);
				}
				workToChange.addPred(shortestWork1);
				
				changed = true;
			}
		}
		return changed;
	}
	
	public void deleteWork() {
	
		ArrayList<Work> works = elementaryWorkArray();
		
		// формуємо масив послідовників
		for (Work W : works) {
			if (W.preds != null
			&& W.preds.contains(this))
				W.preds.remove(this);
		}
		
		this.parent.subWorks.remove(this);
	}
	
	public ArrayList<Work> elementaryWorkArray() {
		ArrayList<Work> Array = new ArrayList<Work>();
		
		// Отримуємо проект
		Work project = this;
		while (project.parent != null)
			project = project.parent;
		
		project.elementaryWorkArray(Array);
		return Array;
	}
	
	private void elementaryWorkArray(ArrayList<Work> Arr) {
		// Якщо робота ПРОСТА
		if (this.subWorks == null) {
			Arr.add(this);
		// Якщо робота СКЛАДНА
		} else {
			for (Work I : this.subWorks) {
				I.elementaryWorkArray(Arr);
			}
		}
	}
	
	public long freeReserve() {
		TreeMap<Long, TreeMap<Work, Boolean>> events = this.getEvents();
		TreeMap<Work, Long> graphic = this.getGraphic();
		TreeMap<Work, ArrayList<Work>> followers = this.getFollowers();
		return this.freeReserve(events, graphic, followers);
	}
	
	private long freeReserve( TreeMap<Long, TreeMap<Work, Boolean>> events, TreeMap<Work, Long> graphic, TreeMap<Work, ArrayList<Work>> followers ) {
		long min = 0;
		long tmp = 0;
		// Якщо ця робота остання у гілці
		if ( followers.get(this).isEmpty() ) {
			// повертаємо залишок часу до кінця проекту після завершення роботи
			return ( events.lastKey() - graphic.get(this) - this.duration);
		// Якщо ця робота всередині гілки
		} else {
			// повертаємо мінімальну
			for (Work W : followers.get(this)) {
				// суму (вільного резерву послідовника) та (проміжку часу між кінцес цієї роботи та початкому послідовника)
				if (( tmp = (W.freeReserve(events, graphic, followers)
						+ (graphic.get(W) - graphic.get(this) - this.duration)) ) < min)
					min = tmp;
			}
		}
		return min;
	}
	
	public long getEnd(TreeMap<Work, Long> graphic) {
		if (this.subWorks == null) {
			return graphic.get(this) + this.duration;
		} else {
			long tmp = 0;
			long end = graphic.get(this.subWorks.get(0));
			for (Work W : this.subWorks) {
				if (( tmp = W.getEnd(graphic)) > end)
					end = tmp;
			}
			return end;
		}
	}
	
	public TreeMap<Long, TreeMap<Work, Boolean>> getEvents() {
		TreeMap<Long, TreeMap<Work, Boolean>> events = new TreeMap<Long, TreeMap<Work, Boolean>>();
		TreeMap<Work, Long> graphic = getGraphic();
		
		// перевіряємо графік робіт
		for (Work W : graphic.keySet()) {
			// якщо перелік подій вже містить подію з початком в цей час
			if (events.containsKey(graphic.get(W)))
				// додаємо в список робіт події ще одну роботу (з міткою true - початок роботи)
				events.get(graphic.get(W)).put(W, true);
			// якщо досі не містить
			else {
				// створюємо новий список робіт
				TreeMap<Work, Boolean> tmp = new TreeMap<Work, Boolean>(new Comparator<Work> () {

					@Override
					public int compare(Work o1, Work o2) {
						return o1.name.compareTo(o2.name);
					}
					
				});
				// заносимо в нього її та інформацію, що це початок роботи (true)
				tmp.put(W, true);
				// створюємо подію зі списком подій
				events.put(graphic.get(W), tmp);
			}
			
			// якщо перелік подій вже містить подію в момент кінця роботи
			if (events.containsKey(graphic.get(W) + W.duration))
				// додаємо до списку робіт події цю роботу
				events.get(graphic.get(W) + W.duration).put(W, false);
			// якщо досі не містить
			else {
				// створюємо новий список робіт
				TreeMap<Work, Boolean> tmp = new TreeMap<Work, Boolean>(new Comparator<Work> () {
					@Override
					public int compare(Work o1, Work o2) {
						return o1.name.compareTo(o2.name);
					}
				});
				// заносимо в нього її та інформацію, що це початок роботи (true)
				tmp.put(W, false);
				// створюємо подію зі списком подій
				events.put(graphic.get(W) + W.duration, tmp);
			}
		}
		
		return events;
	}
	
	public TreeMap<Work, ArrayList<Work>> getFollowers() {
		// буде містити роботу і список її послідовників
		TreeMap<Work, ArrayList<Work>> WorkMap = new TreeMap<Work, ArrayList<Work>>(new Comparator<Work> () {
			@Override
			public int compare(Work o1, Work o2) {
				return o1.name.compareTo(o2.name);
			}
		});
		// список всіх елементарних робіт
		ArrayList<Work> WorkArray = this.elementaryWorkArray();

		// Для кожної роботи перевіряємо
		for (Work W : WorkArray) {
			WorkMap.put(W, new ArrayList<Work>());
			// чи є в списку елементарних робіт
			for (Work follower : WorkArray){
				// її послідовники
				if (follower.preds != null && follower.preds.contains(W)) {
					WorkMap.get(W).add(follower);
				}
			}
		}
		return WorkMap;
	}
	
	public TreeMap<Work,Long> getGraphic() {
		TreeMap<Work,Long> graphic = new TreeMap<Work, Long>(new Comparator<Work> () {
			@Override
			public int compare(Work o1, Work o2) {
				return o1.name.compareTo(o2.name);
			}
		});
		ArrayList<Work> WorkArray = this.elementaryWorkArray();
		TreeMap<Work, ArrayList<Work>> followers = this.getFollowers();
		
		// В циклі переписуємо масив робіт
		for (Work W : WorkArray) {
			graphic.put(W, (long) 0);
		}
		
		boolean changed = true;
		while (changed) {
			changed = false;
			// беремо кожну РОБОТУ З ГРАФІКА
			for (Work W : graphic.keySet()) {
				// кожній її ПОСЛІДОВНИЦІ
				for (Work follower : followers.get(W)) {
					// якщо її ПОТОЧНИЙ ПОЧАТОК меншний від НОВОГО
					if ( graphic.get(follower) < (graphic.get(W) + W.duration) ) {
						// признамо НОВИЙ ПОЧАТОК
						graphic.put(follower, (graphic.get(W) + W.duration) );
						// та фіксуємо зміни
						changed = true;
					}
				}
			}
		}
		return graphic;
	}
	
	public TreeMap<Long, ResourceOverload> getOverloadedIntervals(String res) {
		TreeMap<Long, TreeMap<Work, Boolean>> events = this.getEvents();
		TreeMap<Long, ResourceLoad > ROL = new TreeMap<Long, Work.ResourceLoad >();
		TreeMap<Work, Long> graphic = this.getGraphic();
		
		// перебираємо усі події
		for (Long time : events.keySet()) {
			ResourceLoad  tmpROL = new ResourceLoad ();
			tmpROL.works = new ArrayList<Work>();
			tmpROL.resources = new TreeMap<String, Integer>();
			
			// Перебираємо усі роботи в графіку
			for (Work W : graphic.keySet()) {
				// Перевіряємо кожну роботу чи потрапляє вона в інтервал поточної події
				if (W.res != null
				&& graphic.get(W) <=  time
				&& (graphic.get(W) + W.duration) > time) {
					// Перебираємо ресурси роботи
					for (String S : W.res.keySet()) {
						// І додаємо їх у перелік ресурсів події ROL
						Integer tmpInt = null;
						// Якщо ресурс вже є, додаємо навантаження до колишнього
						if ((tmpInt = tmpROL.resources.get(S)) != null)
							tmpROL.resources.put(S, tmpInt + W.res.get(S));
						else
							// Якщо досі немає - створюємо новий запис
							tmpROL.resources.put(S, W.res.get(S));
					}
					// Після перебору ресурсів додаємо роботу в подію ROL
					tmpROL.works.add(W);
				}
			}
			// Після перебору усіх робіт в графіку додаємо подію в перелік якщо вона не порожня
			if (!tmpROL.works.isEmpty() && !tmpROL.resources.isEmpty())
				ROL.put(time, tmpROL);
		}
		
		TreeMap<Long, ResourceOverload> Overloads = new TreeMap<Long, ResourceOverload>();
		// перебираємо всі події
		for (Long EventStart: ROL.keySet()) {
			// шукаємо цей ресурс в події
			if (ROL.get(EventStart).resources.containsKey(res)
			&& ROL.get(EventStart).resources.get(res) >= 100) {
				ArrayList<Work> tmpWorks = new ArrayList<Work>();
				// перебираємо роботи і додаємо в масив ті, що використовують ресурс
				for (Work W : ROL.get(EventStart).works) {
					if (W.res.containsKey(res)) {
						tmpWorks.add(W);
					}
				}
				
				Overloads.put(EventStart, new ResourceOverload(res, ROL.get(EventStart).resources.get(res), tmpWorks));
			}
		}
		
		if (Overloads.isEmpty())
			return null;
		else
			return Overloads;
	}
	
	public TreeMap<String, Integer> getResources(){
		TreeMap<String, Integer> tmpResMap = new TreeMap<String, Integer>();
		TreeMap<String, Integer> buf = null;
		
		// Якщо ресурси призначено цій робота - повертаємо їх, інакше
		if (res == null) {
			// Якщо підроботи призначено - перебираємо їх
			if (subWorks != null){
				for (Work W : subWorks){
					// якщо підроботі призначено ресурси
					if ((buf = W.getResources()) != null){
						//
						for (String S : buf.keySet()) {
							if (tmpResMap.containsKey(S))
								tmpResMap.put(S,tmpResMap.get(S) + buf.get(S));
							else
								tmpResMap.put(S,buf.get(S));
						}
					}
				}
				if (tmpResMap.isEmpty())
					return null;
				else
					return tmpResMap;
			} else
				return null;
		} else 
			return res;
	}
	
	public long getStart(TreeMap<Work, Long> graphic) {
		if (this.subWorks == null) {
			return graphic.get(this);
		} else {
			long tmp = 0;
			long begin = graphic.get(this.subWorks.get(0));
			for (Work W : this.subWorks) {
				if (( tmp = W.getStart(graphic)) < begin)
					begin = tmp;
			}
			return begin;
		}
	}
	
	public Work getWork(String name){
		if (this.subWorks != null) {
			for (Work W : this.subWorks)
				if (W.name.equals(name))
					return W;
		}
		return null;
	}
	
	class ResourceLoad {
		ArrayList<Work> works;
		TreeMap<String, Integer> resources;
	}
	
	class ResourceOverload {
		String resource;
		Integer overload;
		ArrayList<Work> works;
		
		ResourceOverload (String res, Integer over, ArrayList<Work> works) {
			this.works = works;
			resource = res;
			overload = over;
		}
	}

	public boolean isCritical() {
		long endOfProject = this.getEvents().lastKey();
		TreeMap<Work, Long> graphic = this.getGraphic();
		TreeMap<Work, ArrayList<Work>> followers = this.getFollowers();
		
		// якщо робота складна
		if (this.subWorks != null) {
			// перевіряємо підроботи
			for (Work W : this.subWorks) {
				// і в разі критичності
				if (W.isCritical(endOfProject, graphic, followers)) {
					// ЦЯ робота також критична
					return true;
				}
			}
			return false;
		} else
			return isCritical(endOfProject, graphic, followers);
	}
	
	private boolean isCritical(long endOfProject, TreeMap<Work, Long> graphic, TreeMap<Work, ArrayList<Work>> followers) {
		// перевіряємо роботи в графіку
		for (Work W : graphic.keySet()) {
			// знаходимо ЦЮ роботу
			if (W.equals(this))
				// якщо вона закінчується одночасно з проектом,
				if ((graphic.get(W) + W.duration) == endOfProject)
					// то вона критична
					return true;
				else {
					// інакше перевіряємо на критичність усіх послідовників
					// для цього знаходимо послідовників ЦІЄЇ роботи
					ArrayList<Work> thisWorkFollowers = followers.get(this);
					
					// якщо послідовники існують
					if (thisWorkFollowers != null) {
						// перебираємо їх
						for (Work WF : thisWorkFollowers) {
							// і в разі критичності
							// порівнюємо момент їх завершення 
							// з початком цієї роботи
							if (graphic.get(WF) == (graphic.get(this) + this.duration ) 
							&& WF.isCritical(endOfProject, graphic, followers))
								// ЦЯ робота також критична
								return true;
						}
					}
				}
		}
		return false;
	}
	
	public static Work loadProject(String path) {
		Work Curs = null;
		/*
		try ( ObjectInputStream objIS = new ObjectInputStream(new FileInputStream(path)) ) { 
			Curs = (Work)objIS.readObject();
		} catch (Exception e) { */
		try { 
			ObjectInputStream objIS = new ObjectInputStream(new FileInputStream(path));
			Curs = (Work)objIS.readObject();
			objIS.close();
		} catch (Exception e) {
			System.out.println("Проект не знайдено");
		}
		return Curs;
	}
	
	public void saveProject(String path) {
		Work pr = this;
		while (pr.parent != null)
			pr = pr.parent;
		
		//pr.out.close();
		pr.out = null;
		
		/*try ( ObjectOutputStream objOS = new ObjectOutputStream(new FileOutputStream(path)) ) {
			objOS.writeObject(pr);
		} catch (Exception e) { */
		try { 
			ObjectOutputStream objOS = new ObjectOutputStream(new FileOutputStream(path));
			objOS.writeObject(pr);
			objOS.close();
		} catch (Exception e) {
			System.out.println("Проект не збережено");
		}
	}

	public void setPrices(){
		if (parent == null){
			TreeMap<String, Integer> tmpMap;
			if ((tmpMap = getResources()) != null){
				if (resPrices == null) {
					this.resPrices = new TreeMap<String, Integer>();
				}
				
				for (String S : tmpMap.keySet()) {
					if (resPrices.get(S) == null) {
						resPrices.put(S, -1);
					}
				}
				
				//----------------------------------------
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					
					String data = null;
					out().println("Введіть ставку для ресурсу у ГРН/ГОД, яку він може виконати.");
					for (String S : resPrices.keySet()) {
						if (resPrices.get(S) == -1){
							out().println("Для ресурсу \"" + S +"\": ");
							if (!(data = br.readLine()).equalsIgnoreCase("вихід")) {
								resPrices.put(S, new Integer(data));
							} else {
								out().println("Введення ставки на ресурси перервано");
								break;
							}
						}
					}
					br.close();
				} catch (IOException e) { out().println("Горе!"); }
				out().println("Введення ставки на ресурси завершено.");
				//----------------------------------------
			} else {
				out().println("Жодного ресурсу на цю роботу не було призначено");
				return;
			}
		} else {
			out().println("Ця робота не є проектом!");
		}
	}

	public void showGuntt() {
		TreeMap<Work, Long> qwe1 = this.getGraphic();
		
		Work pr = this;
		while (pr.parent != null)
			pr = pr.parent;
		
		if (qwe1 != null)
			pr.showGuntt(qwe1);
	}
	
	private void showGuntt(TreeMap<Work, Long> graphic) {
			// Якщо ПРОСТА
			if (this.subWorks == null) {
				String spaces = "";
				String sticks = "";
				for (int i = 0; i < (0.6*graphic.get(this)/3600000); i++)
					spaces = spaces.concat(" ");
						
				for (int i = 0; i < (0.6*this.duration/3600000); i++)
					sticks = sticks.concat("|");
				out().println(spaces + sticks);
				out().println(spaces 
						+ new Long(graphic.get(this)/3600000) + " год " 
						+ this + " (" + this.duration/3600000 + " год) резерв часу " + this.freeReserve()/3600000);
			// якщо СКЛАДНА
			} else {
				//----------------------------------
				long begin = this.getStart(graphic);
				long end = this.getEnd(graphic);
				String spaces = "";
				String sticks = "|";
				for (int i = 0; i < (0.6*begin/3600000); i++)
					spaces = spaces.concat(" ");
						
				for (int i = 1; i < ((0.6*(end - begin)/3600000) - 1); i++)
					sticks = sticks.concat("-");
				sticks = sticks.concat("|");
				out().println(spaces + sticks);
				out().println(spaces 
						+ new Long(begin/3600000) + " год " 
						+ this + " (" + (end - begin)/3600000 + " год)");
				//-----------------------------------
				for (Work subW : this.subWorks)
					subW.showGuntt(graphic);
			}
	}
	
	public void showPlan() {
		showPlan(0);
	}
	
	private void showPlan(int q){
		for(int i=0; i<q; i++){
			out().print('\t');
		}
		
		String tmpRes = null;
		TreeMap<String, Integer> resources = this.getResources();
		if (resources != null) {
			if (subWorks == null) {
				tmpRes = "";
				for (String S : resources.keySet()) {
					tmpRes = tmpRes.concat(S + " " + resources.get(S) + ", ");
				}
				tmpRes = tmpRes.substring(0, tmpRes.length() - 2);
			} else {
				tmpRes = "";
				for (String S : resources.keySet()) {
					tmpRes = tmpRes.concat(S + ", ");
				}
				tmpRes = tmpRes.substring(0, tmpRes.length() - 2);
			}
		}
		
		String dure = (this.subWorks != null || this.duration == 0)
						? null
						: new String(new Long(duration/3600000).toString() + " годин");
		
		out().println("\"" + this.name + "\""
				+ ((dure != null ) 
						? " триває: [".concat(dure).concat("],")
						: "")
				+ ((this.preds != null) 
						? (" попередники: " + this.preds).concat(",")
						: "")
				+ ((resources != null) 
						? " ресурси: ".concat(tmpRes).concat(";")
						: " [---]" ));
		
		if(subWorks != null){
			ArrayList<Work> tmpSubWorks = this.subWorks;
			for(Work R: tmpSubWorks){
				R.showPlan(q+1);
			}
		}
	}
	
 	public void showProjectCost() {
		// Якщо ставка та потужності ресурсів введені, можна обчислити вартість проекту
		if (this.resPrices != null) {
			ArrayList<Work> works = this.elementaryWorkArray();
			int projectCost = 0;
			
			// отримуємо вказівник на проект
			Work pr = this;
			while (this.parent != null)
				pr = pr.parent;
			 
			TreeMap<String,Integer> resourceCost = new TreeMap<String, Integer>();
			
			// Для кожної роботи 
			if (works != null)
			for (Work W : works) {
				// перебираємо ресурси
				if (W.res != null)
				for (String res : W.res.keySet()) {
						// додаємо його ЗАВАНТАЖЕННЯ * ТРИВАЛІСТЬ = [ год ]
						resourceCost.put(res, 
										(int) (( (resourceCost.containsKey(res))
														? (resourceCost.get(res)) 
														: 0) 
										+ (W.res.get(res)/100.0 * (W.duration/3600000))));
				}
			}
			
			// Домножуємо значення в [ год ] на ставку [ грн / год ]і отримуємо [ грн ]
			int tmpCost = 0;
			for (String res : resourceCost.keySet()) {
				resourceCost.put(res, (tmpCost = (int) (resourceCost.get(res) * resPrices.get(res))));
				projectCost += tmpCost;
				out().println("Ресурс " + res + " коштує проекту " + tmpCost + " грн");
			}
			
			out().println("Вартість всього проекту: " + projectCost + " грн");
		} else {
			out().println("Ресурсам не призначено ставки.");
		}
	}

 	public void showResourcesOverload() {
		Work pr = this;
		while( pr.parent != null )
			pr = pr.parent;
		TreeMap<String, Integer> resources = pr.getResources();
	
		for (String res : resources.keySet()) {
			TreeMap<Long, ResourceOverload> Overloads = getOverloadedIntervals(res);
			
			if (Overloads != null) {
				out().println("\nРесурс \"" + res + "\"");
				for (Long L : Overloads.keySet()) {
					out().println(" " + L/3600000 + " год, " + Overloads.get(L).resource + ", завантаження: " + Overloads.get(L).overload);
					for (Work W : Overloads.get(L).works) {
						out().println("   " + W.name + " завантажує ресурс на " + W.res.get(res));
					}
				}
			}
		}
	}

	public String toString() {
		return this.name;
	}
}