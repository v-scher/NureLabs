package ua.nure.course3.MiZUP.coursework.Shcherbatenko;
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
	ArrayList<Work> preds; // ������, �� ��������� ����
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
		// ���� �� ������ ������
		if(subWorks == null && duration != 0){ 
			// �������� �������� ���������� �������� ������� ���������
			if (PercentageOfEmployment > 100) {
				out().println("��������� ������� \"" + name + "\" �������� �� 100%");
				// ����������� �� �������� ��������
				PercentageOfEmployment = 100;
			}
			// ������� ��'� �������
			name = name.toLowerCase();
			
			// ���� ������� �� �� ���������� (�������� - ������ ������)
			if(res == null ){ 
				// ��������� ������������ ����� ��� �������
				res = new TreeMap<String, Integer>();
			} else {
				// ������ ���������� �� �� ������������ ������
				if (res.get(name) != null)
					out().println("������ \"" + name + "\" ������������");
			}
			// �������� �����
			res.put(name, PercentageOfEmployment);
		} else {
			out().println("\"" + name + "\" �� ������ \"" + this.name + "\" �� ������ - �������� ������� ������� ������������� �����������");
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
			out().println("��������� ������ \"" + workName + "\" �� ���� ���������");
			return null;
		}
		
		Object tmpArrPreds[] = null;
		// ���� ������ �� �� ����������
		if (subWorks == null) {
			subWorks = new ArrayList<Work>();
			if (this.preds != null) {
				tmpArrPreds = this.preds.toArray();
				out().println("����������� ������ \"" + this.name + "\" ��������, ���������� ������ \"" + workName + "\"");
				this.preds = null;
			}
				
			if (this.res != null) {
				out().println("������� \"" + this.name + "\" ��������, ���������� ������ \"" + workName + "\"");
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
			out().println("������ \"" + workName +"\" ��� ����������.");
			return null;
		}
	}

	public Work addPred(Work W) {
		// ���� ������ �������
		if (this.subWorks != null) {
			for (Work I : this.subWorks)
				I.addPred(W);
		} else {
			// ������� ���� ��� ϲ������ �������
			if (this.parent == null) {
				out().println("����������� ������ ���� ���� �������� �������");
				return null;
			}
			if (this.equals(W)) {
				out().println("������ " + W.name + " �� ���� ���� ������� ������������");
				return null;
			}
				
			if ( ! this.ableToPred(W)) {
				out().println("����! ������ \"" + W.name + "\" �� ���� ���� ������������ \"" + this.name + "\"");
				return null;
			}
		
			if (this.preds == null)
				this.preds = new ArrayList<Work>();		
				
			if (this.preds.contains(W)) {
				out().println("������ ���������� \"" + W.name + "\" ��� ���������� ����� \"" + this.name + "\"");
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
		// ������� ������� �� ������������� �������� �������
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
		
		// ��������� ������� ��������� � ������� ������������
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
		// ���������� ������ �� ��������������
		TreeMap<Long, ResourceOverload> intervals;
		// ����� ������������ �������� ������ ����������� �����������
		boolean changed = false;
		while ( (intervals =  this.getOverloadedIntervals(resArg)) != null) {
			//---------------------------------------------------------------------------------------------------------------------
			this.showGuntt();
			out().println(resArg);
			Long L = intervals.firstKey();
			out().println(L/3600000 + " ���, �������������� " + intervals.get(L).overload);
			for (Work W : intervals.get(L).works)
				out().println(W + " " + W.res.get(resArg));
			out().println("                                    --//--//--//--//--//--�������� �����������--//--//--//--//--//--");
			//---------------------------------------------------------------------------------------------------------------------
			final TreeMap<Work, Long> graphic = this.getGraphic();
			ArrayList<Work> problemWorks = intervals.firstEntry().getValue().works;
			
			ArrayList<Work> crWorks = new ArrayList<Work>();
			ArrayList<Work> notCrWorks = new ArrayList<Work>();
			int crLoad = 0;
			
			// ����²�ߪ�� ������, �� �������������� ������ �� ����������
			for (Work W : problemWorks) {
				if (W.isCritical()) {
					// � ��������� ������ ������������ ���������
					crLoad += W.res.get(resArg);
					crWorks.add(W);
				} else {
					// � ����������� ����
					notCrWorks.add(W);
				}
			}
			
			// ������� ����� ���� ��� ��������
			int loading = 0;
			ArrayList<Work> unCompatibleWorks = new ArrayList<Work>();
			// ��� ������� �������� ���� �Ѳ ������� ������
			for (Work W : problemWorks) {
				unCompatibleWorks.add(W);
			}
			
			// �������� ����� ����������� ������ ��� ��������� ���������� (�� �� ���� ����������)
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
			
			// ���� ����� �������� ���ʲ ��������� ������
			if (crLoad <= 100) {
				// ��������� � ������� ���� ��� �������� �� �������
				for (Work W : crWorks) {
					// ������ ��������� ��� ������ ������� ���� ��������
					loading += W.res.get(resArg);
					// ���������� ��, ���� ���������
					unCompatibleWorks.remove(W);
				}
			} else {
				// �������� �� ������������ ��������� � ������� ������������ ����
				Collections.sort(crWorks, new Comparator<Work>() {
					@Override
					public int compare(Work arg0, Work arg1) {
						long endOfWork = 0;
						// �������� �, �� ����������� �����
						if ( (endOfWork = (graphic.get(arg0) + arg0.duration - graphic.get(arg1) - arg1.duration)) != 0 )
							return (int) endOfWork;
						// �������� ��������
						if (arg1.duration != arg0.duration)
							return (int) (arg1.duration - arg0.duration);
						// �������� ������� ����������
						return (int) (arg1.res.get(resArg) - arg0.res.get(resArg));
					}
				});
				
				for (Work W : crWorks) {
					if ( crLoad > 100 ) {
						// ������ ��������� ��� ������ ������� ���� ��������
						crLoad -= W.res.get(resArg);
						// �������� �� ������� ��, ���� ������� ������������
						unCompatibleWorks.remove(W);
					} else
						break;
				} 
			}
			
			// ���������� ��������� ������ �� ����, �� ����� �� �������� ���������� � ����������
			for (Work W : notCrWorks) {
				// ���� �����, �� 
				if ((W.res.get(resArg) + loading) <= 100) {
					// ������ ������������ �������
					loading += W.res.get(resArg);
					// � ��������� � ������ ��������� ����
					unCompatibleWorks.remove(W);
				}
			}
			
			// ��������� ����� ������� ������, ��� ���������� ����� �� �����
			Work shortestWork1 = null;
			long shortestStart = 0;
			
			// ����� ���������� �� ������ ������
			for (int i = 0; i < problemWorks.size(); i++) {
				if ( ! unCompatibleWorks.contains(problemWorks.get(i))) {
					// ������ ������� ������ � �������
					Work current1;
					long currentStart = graphic.get( current1 = problemWorks.get(i) );
					
					if (shortestWork1 == null
					|| (shortestStart + shortestWork1.duration) > (currentStart + current1.duration)) {
						shortestWork1 = current1;
						shortestStart = currentStart;
					}
				}
			}
			
			// ��������������� ������
			for (Work workToChange : unCompatibleWorks) {
				out().println("----- ����� " + workToChange + " ���������� ����������� " + shortestWork1);
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
		
		// ������� ����� �����������
		for (Work W : works) {
			if (W.preds != null
			&& W.preds.contains(this))
				W.preds.remove(this);
		}
		
		this.parent.subWorks.remove(this);
	}
	
	public ArrayList<Work> elementaryWorkArray() {
		ArrayList<Work> Array = new ArrayList<Work>();
		
		// �������� ������
		Work project = this;
		while (project.parent != null)
			project = project.parent;
		
		project.elementaryWorkArray(Array);
		return Array;
	}
	
	private void elementaryWorkArray(ArrayList<Work> Arr) {
		// ���� ������ ������
		if (this.subWorks == null) {
			Arr.add(this);
		// ���� ������ �������
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
		// ���� �� ������ ������� � ����
		if ( followers.get(this).isEmpty() ) {
			// ��������� ������� ���� �� ���� ������� ���� ���������� ������
			return ( events.lastKey() - graphic.get(this) - this.duration);
		// ���� �� ������ �������� ����
		} else {
			// ��������� ��������
			for (Work W : followers.get(this)) {
				// ���� (������� ������� �����������) �� (������� ���� �� ����� ���� ������ �� ��������� �����������)
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
		
		// ���������� ������ ����
		for (Work W : graphic.keySet()) {
			// ���� ������ ���� ��� ������ ���� � �������� � ��� ���
			if (events.containsKey(graphic.get(W)))
				// ������ � ������ ���� ��䳿 �� ���� ������ (� ����� true - ������� ������)
				events.get(graphic.get(W)).put(W, true);
			// ���� ��� �� ������
			else {
				// ��������� ����� ������ ����
				TreeMap<Work, Boolean> tmp = new TreeMap<Work, Boolean>(new Comparator<Work> () {

					@Override
					public int compare(Work o1, Work o2) {
						return o1.name.compareTo(o2.name);
					}
					
				});
				// �������� � ����� �� �� ����������, �� �� ������� ������ (true)
				tmp.put(W, true);
				// ��������� ���� � ������� ����
				events.put(graphic.get(W), tmp);
			}
			
			// ���� ������ ���� ��� ������ ���� � ������ ���� ������
			if (events.containsKey(graphic.get(W) + W.duration))
				// ������ �� ������ ���� ��䳿 �� ������
				events.get(graphic.get(W) + W.duration).put(W, false);
			// ���� ��� �� ������
			else {
				// ��������� ����� ������ ����
				TreeMap<Work, Boolean> tmp = new TreeMap<Work, Boolean>(new Comparator<Work> () {
					@Override
					public int compare(Work o1, Work o2) {
						return o1.name.compareTo(o2.name);
					}
				});
				// �������� � ����� �� �� ����������, �� �� ������� ������ (true)
				tmp.put(W, false);
				// ��������� ���� � ������� ����
				events.put(graphic.get(W) + W.duration, tmp);
			}
		}
		
		return events;
	}
	
	public TreeMap<Work, ArrayList<Work>> getFollowers() {
		// ���� ������ ������ � ������ �� �����������
		TreeMap<Work, ArrayList<Work>> WorkMap = new TreeMap<Work, ArrayList<Work>>(new Comparator<Work> () {
			@Override
			public int compare(Work o1, Work o2) {
				return o1.name.compareTo(o2.name);
			}
		});
		// ������ ��� ������������ ����
		ArrayList<Work> WorkArray = this.elementaryWorkArray();

		// ��� ����� ������ ����������
		for (Work W : WorkArray) {
			WorkMap.put(W, new ArrayList<Work>());
			// �� � � ������ ������������ ����
			for (Work follower : WorkArray){
				// �� �����������
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
		
		// � ���� ���������� ����� ����
		for (Work W : WorkArray) {
			graphic.put(W, (long) 0);
		}
		
		boolean changed = true;
		while (changed) {
			changed = false;
			// ������ ����� ������ � ���Բ��
			for (Work W : graphic.keySet()) {
				// ����� �� ���˲�����ֲ
				for (Work follower : followers.get(W)) {
					// ���� �� �������� ������� ������� �� ������
					if ( graphic.get(follower) < (graphic.get(W) + W.duration) ) {
						// �������� ����� �������
						graphic.put(follower, (graphic.get(W) + W.duration) );
						// �� ������� ����
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
		
		// ���������� �� ��䳿
		for (Long time : events.keySet()) {
			ResourceLoad  tmpROL = new ResourceLoad ();
			tmpROL.works = new ArrayList<Work>();
			tmpROL.resources = new TreeMap<String, Integer>();
			
			// ���������� �� ������ � �������
			for (Work W : graphic.keySet()) {
				// ���������� ����� ������ �� ��������� ���� � �������� ������� ��䳿
				if (W.res != null
				&& graphic.get(W) <=  time
				&& (graphic.get(W) + W.duration) > time) {
					// ���������� ������� ������
					for (String S : W.res.keySet()) {
						// � ������ �� � ������ ������� ��䳿 ROL
						Integer tmpInt = null;
						// ���� ������ ��� �, ������ ������������ �� ����������
						if ((tmpInt = tmpROL.resources.get(S)) != null)
							tmpROL.resources.put(S, tmpInt + W.res.get(S));
						else
							// ���� ��� ���� - ��������� ����� �����
							tmpROL.resources.put(S, W.res.get(S));
					}
					// ϳ��� �������� ������� ������ ������ � ���� ROL
					tmpROL.works.add(W);
				}
			}
			// ϳ��� �������� ��� ���� � ������� ������ ���� � ������ ���� ���� �� �������
			if (!tmpROL.works.isEmpty() && !tmpROL.resources.isEmpty())
				ROL.put(time, tmpROL);
		}
		
		TreeMap<Long, ResourceOverload> Overloads = new TreeMap<Long, ResourceOverload>();
		// ���������� �� ��䳿
		for (Long EventStart: ROL.keySet()) {
			// ������ ��� ������ � ��䳿
			if (ROL.get(EventStart).resources.containsKey(res)
			&& ROL.get(EventStart).resources.get(res) >= 100) {
				ArrayList<Work> tmpWorks = new ArrayList<Work>();
				// ���������� ������ � ������ � ����� �, �� �������������� ������
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
		
		// ���� ������� ���������� ��� ������ - ��������� ��, ������
		if (res == null) {
			// ���� �������� ���������� - ���������� ��
			if (subWorks != null){
				for (Work W : subWorks){
					// ���� ������� ���������� �������
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
		
		// ���� ������ �������
		if (this.subWorks != null) {
			// ���������� ��������
			for (Work W : this.subWorks) {
				// � � ��� ����������
				if (W.isCritical(endOfProject, graphic, followers)) {
					// �� ������ ����� ��������
					return true;
				}
			}
			return false;
		} else
			return isCritical(endOfProject, graphic, followers);
	}
	
	private boolean isCritical(long endOfProject, TreeMap<Work, Long> graphic, TreeMap<Work, ArrayList<Work>> followers) {
		// ���������� ������ � �������
		for (Work W : graphic.keySet()) {
			// ��������� �� ������
			if (W.equals(this))
				// ���� ���� ���������� ��������� � ��������,
				if ((graphic.get(W) + W.duration) == endOfProject)
					// �� ���� ��������
					return true;
				else {
					// ������ ���������� �� ���������� ��� �����������
					// ��� ����� ��������� ����������� ֲ�� ������
					ArrayList<Work> thisWorkFollowers = followers.get(this);
					
					// ���� ����������� �������
					if (thisWorkFollowers != null) {
						// ���������� ��
						for (Work WF : thisWorkFollowers) {
							// � � ��� ����������
							// ��������� ������ �� ���������� 
							// � �������� ���� ������
							if (graphic.get(WF) == (graphic.get(this) + this.duration ) 
							&& WF.isCritical(endOfProject, graphic, followers))
								// �� ������ ����� ��������
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
			System.out.println("������ �� ��������");
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
			System.out.println("������ �� ���������");
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
					out().println("������ ������ ��� ������� � ���/���, ��� �� ���� ��������.");
					for (String S : resPrices.keySet()) {
						if (resPrices.get(S) == -1){
							out().println("��� ������� \"" + S +"\": ");
							if (!(data = br.readLine()).equalsIgnoreCase("�����")) {
								resPrices.put(S, new Integer(data));
							} else {
								out().println("�������� ������ �� ������� ���������");
								break;
							}
						}
					}
					br.close();
				} catch (IOException e) { out().println("����!"); }
				out().println("�������� ������ �� ������� ���������.");
				//----------------------------------------
			} else {
				out().println("������� ������� �� �� ������ �� ���� ����������");
				return;
			}
		} else {
			out().println("�� ������ �� � ��������!");
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
			// ���� ������
			if (this.subWorks == null) {
				String spaces = "";
				String sticks = "";
				for (int i = 0; i < (0.6*graphic.get(this)/3600000); i++)
					spaces = spaces.concat(" ");
						
				for (int i = 0; i < (0.6*this.duration/3600000); i++)
					sticks = sticks.concat("|");
				out().println(spaces + sticks);
				out().println(spaces 
						+ new Long(graphic.get(this)/3600000) + " ��� " 
						+ this + " (" + this.duration/3600000 + " ���) ������ ���� " + this.freeReserve()/3600000);
			// ���� �������
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
						+ new Long(begin/3600000) + " ��� " 
						+ this + " (" + (end - begin)/3600000 + " ���)");
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
						: new String(new Long(duration/3600000).toString() + " �����");
		
		out().println("\"" + this.name + "\""
				+ ((dure != null ) 
						? " �����: [".concat(dure).concat("],")
						: "")
				+ ((this.preds != null) 
						? (" �����������: " + this.preds).concat(",")
						: "")
				+ ((resources != null) 
						? " �������: ".concat(tmpRes).concat(";")
						: " [---]" ));
		
		if(subWorks != null){
			ArrayList<Work> tmpSubWorks = this.subWorks;
			for(Work R: tmpSubWorks){
				R.showPlan(q+1);
			}
		}
	}
	
 	public void showProjectCost() {
		// ���� ������ �� ��������� ������� ������, ����� ��������� ������� �������
		if (this.resPrices != null) {
			ArrayList<Work> works = this.elementaryWorkArray();
			int projectCost = 0;
			
			// �������� �������� �� ������
			Work pr = this;
			while (this.parent != null)
				pr = pr.parent;
			 
			TreeMap<String,Integer> resourceCost = new TreeMap<String, Integer>();
			
			// ��� ����� ������ 
			if (works != null)
			for (Work W : works) {
				// ���������� �������
				if (W.res != null)
				for (String res : W.res.keySet()) {
						// ������ ���� ������������ * �����˲��� = [ ��� ]
						resourceCost.put(res, 
										(int) (( (resourceCost.containsKey(res))
														? (resourceCost.get(res)) 
														: 0) 
										+ (W.res.get(res)/100.0 * (W.duration/3600000))));
				}
			}
			
			// ��������� �������� � [ ��� ] �� ������ [ ��� / ��� ]� �������� [ ��� ]
			int tmpCost = 0;
			for (String res : resourceCost.keySet()) {
				resourceCost.put(res, (tmpCost = (int) (resourceCost.get(res) * resPrices.get(res))));
				projectCost += tmpCost;
				out().println("������ " + res + " ����� ������� " + tmpCost + " ���");
			}
			
			out().println("������� ������ �������: " + projectCost + " ���");
		} else {
			out().println("�������� �� ���������� ������.");
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
				out().println("\n������ \"" + res + "\"");
				for (Long L : Overloads.keySet()) {
					out().println(" " + L/3600000 + " ���, " + Overloads.get(L).resource + ", ������������: " + Overloads.get(L).overload);
					for (Work W : Overloads.get(L).works) {
						out().println("   " + W.name + " ��������� ������ �� " + W.res.get(res));
					}
				}
			}
		}
	}

	public String toString() {
		return this.name;
	}
}