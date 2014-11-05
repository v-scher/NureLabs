package ua.khture.shcherbatenko.crppr.LabWork3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import ua.khture.shcherbatenko.crppr.LabWork2.Animal;
import ua.khture.shcherbatenko.crppr.LabWork2.mammals.Cow;
import ua.khture.shcherbatenko.crppr.LabWork2.mammals.Dog;
import ua.khture.shcherbatenko.crppr.LabWork2.mammals.Elephant;
import ua.khture.shcherbatenko.crppr.LabWork2.reptiles.Crocodile;
import ua.khture.shcherbatenko.crppr.LabWork2.reptiles.Lizard;
import ua.khture.shcherbatenko.crppr.LabWork2.reptiles.Snake;

public class ScherLinkedList<T extends Animal> implements Iterable<T>{
	ScherEntry<T> root;

	public ScherLinkedList() {
		root = null;
	}

	public boolean add(T newItem) {
		if( root == null){
			root = new ScherEntry<T>(newItem);
			return true;
		}
		
		ScherEntry<T> entry = root;
		while(entry.nextEntry != null)
			entry = entry.nextEntry;
		entry.nextEntry = new ScherEntry<>(newItem);
		
		return true;
	}

	public void set(int index, T newItem) {
		if( index < 0
		|| index > size())
			throw new IndexOutOfBoundsException();
		
		if( index == 0){
			root = new ScherEntry<T>(newItem, root);
		} else {
			// searching the previous entry
			int search = 0;
			ScherEntry<T> previousEntry = root;
			
			while(search < index-1){
				previousEntry = previousEntry.nextEntry;
				search++;
			}
			
			previousEntry.nextEntry = new ScherEntry<T>(newItem, previousEntry.nextEntry);
		}
	}

	public void clear() {
		root = null;
	}

	public boolean contains(T item) {
		ScherEntry<T> entry = root;
		while(entry.nextEntry != null){
			if( entry.item.equals(item)) return true;
			entry = entry.nextEntry;
		}
		
		return false;
	}

	public T get(int index) {
		check(index);
		
		int search = 0;
		ScherEntry<T> entry = root;
		
		while(search < index){
			entry = entry.nextEntry;
			search++;
		}
		
		return entry.item;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean remove(T item) {
		if( isEmpty() ) return false;
		
		if( root.item.equals(item)){
			root = root.nextEntry;
			return true;
		}
		
		ScherEntry<T> previousEntry = root;
		
		try{
			while( ! previousEntry.nextEntry.item.equals(item)){
				previousEntry = previousEntry.nextEntry;
			}
		} catch (NullPointerException e){
			return false;
		}
		
		previousEntry.nextEntry = previousEntry.nextEntry.nextEntry; 
		return true;
	}

	public T remove(int index) {
		check(index);
		
		if( index == 0){
			ScherEntry<T> removingElement = root;
			root = root.nextEntry;
			return removingElement.item;
		} else {
			// searching the previous entry
			int search = 0;
			ScherEntry<T> previousEntry = root;
			
			while(search < index-1){
				previousEntry = previousEntry.nextEntry;
				search++;
			}
			
			ScherEntry<T> removingElement = previousEntry.nextEntry;
			previousEntry.nextEntry = removingElement.nextEntry;
			return removingElement.item;
		}
	}

	public int size() {
		int size = 0;
		ScherEntry<T> entry = root;
		
		while(entry != null){
			size++;
			entry = entry.nextEntry;
		}
			
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			ScherEntry<T> entry = null;
			
			@Override
			public boolean hasNext() {
				if( root == null)
					return false;
				else if( entry == null)
					return true;
				else {
					return (entry.nextEntry != null);
				}
			}

			@Override
			public T next() {
				if( entry == null) {
					entry = root;
				} else
					entry = entry.nextEntry;
				
				return entry.item;
			}

			@Override
			public void remove() {
				ScherLinkedList.this.remove(entry.item);
			}
		};
	}

	private void check(int index) {
		if( index < 0
		|| index >= size())
			throw new IndexOutOfBoundsException();
	}
	
	public static void main(String args[]){
		ScherLinkedList<Animal> list = new ScherLinkedList<>();
		System.out.println((list.isEmpty()) ? "The list is EMPTY" : "Whatta?!");
		System.out.println();

		System.out.println("5 animals added");
		list.add(new Cow(30));
		list.add(new Crocodile(50));
		list.add(new Elephant(10));
		list.add(new Dog(36));
		list.add(new Lizard(20));
		System.out.println();

		System.out.println("List content");
		for( Animal A : list)
			System.out.println("  " + A);
		System.out.println();
		
		System.out.println("Last words of some animal: " + list.remove(0));
		System.out.println();

		System.out.println("List content");
		for( Animal A : list)
			System.out.println("  " + A);
		System.out.println();

		System.out.println("2 animals added");
		list.add(new Elephant(20));
		list.add(new Snake(30));
		System.out.println();

		System.out.println("List content");
		for( Animal A : list)
			System.out.println("  " + A);
		System.out.println();

		System.out.println("Sorting animals");
		ArrayList<Animal> zoo = new ArrayList<>();
		for( Animal A : list)
			zoo.add(A);
		Collections.sort(zoo);
		System.out.println();
		
		System.out.println("List content");
		for( Animal A : zoo)
			System.out.println("  " + A);
		System.out.println();
	}
}

class ScherEntry <T> {
	public ScherEntry(T newItem) {
		item = newItem;
		nextEntry = null;
	}
	
	public ScherEntry(T newItem, ScherEntry<T> nextEntry) {
		item = newItem;
		this.nextEntry = nextEntry;
	}
	
	T item;
	ScherEntry<T> nextEntry;
}
