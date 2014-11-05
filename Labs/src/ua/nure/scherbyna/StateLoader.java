package ua.nure.scherbyna;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import ua.nure.scherbyna.containers.State;
import ua.nure.scherbyna.containers.Worker;

public class StateLoader {
	public static final String PATH = "courseWork.state";
	public static final String PATH_RES = "res/courseWork.state";
	
	public static State getState()
	{
		State state = loadState(PATH_RES);
		state = (state == null) ? loadState(PATH) : state;
		
		if (state != null)
		{
			Map<Integer, Worker> workers = state.getWorkers();
			System.out.println("loaded workers " + workers.size());
			for (Integer ID : workers.keySet())
				System.out.println(ID + " " + workers.get(ID).name + " " + workers.get(ID).appointment + " " + workers.get(ID).payment);
			
			return state;
		}else
		{
			System.out.println(" null ");
			return new State();
		}
	}

	public static void saveState(State _state)
	{
		try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(PATH)))
		{
			obj.writeObject(_state);
		} catch (Exception e) {
			System.out.println("error during saving " + e);
		}
	}
	
	protected static State loadState(String _fullPath)
	{
		State state = null;
		try (ObjectInputStream inputStrem = new ObjectInputStream(new FileInputStream(new File(_fullPath)))){
			state = (State) inputStrem.readObject();
		} catch (Exception e){
			System.out.println("error during loading " + e);
		}
		return state;
	}
}
