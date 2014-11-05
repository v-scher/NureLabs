package other;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

public class FileRenamer {
	static String path = "D:\\YandexDisk\\Pictures\\12.12.29 Ëüג³ג Íמגטי נ³ך";
	static String prefix = "LVIV_NY_2013_";
	static int number = 0;
	
	
	public static void p(String _text)
	{
		System.out.println(_text);
	}
	
	public static void main(String[] args)
	{
		//chronologyRestore();
		//dateRestore();
		// restoreNamesAfterCleaning();
	}
	
	public static void imageMetadataRestore()
	{
		
	}
	
	public static void dateRestore()
	{
		File rootDir = new File(path);
		File[] pictures = rootDir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File arg0, String arg1) {
					return arg1.endsWith(".jpg") ||
							arg1.endsWith(".JPG");
				}
			});

			Arrays.sort(pictures, new Comparator<File>(){
				@Override
				public int compare(File _f1, File _f2) {
					String name1 = _f1.getName();
					String name2 = _f2.getName();
					return name1.compareTo(name2);
				}
			});
			
			for (File pic : pictures)
			{
				p(pic.getName());
				/*
				File newFile = new File(path + "\\" + prefix + ++number + ".JPG");
				pic.renameTo(newFile);
				pic.delete();*/
			}
	}

	public static void restoreNamesAfterCleaning()
	{
		File rootDir = new File(path);
		File[] pictures = rootDir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File arg0, String arg1) {
					return arg1.endsWith(".jpg") ||
							arg1.endsWith(".JPG") ||
							arg1.endsWith(".3gp") ||
							arg1.endsWith(".3GP") ||
							arg1.endsWith(".mp4") ||
							arg1.endsWith(".MP4") ||
							arg1.endsWith(".avi") ||
							arg1.endsWith(".AVI");
				}
			});

		Arrays.sort(pictures, new Comparator<File>(){
			@Override
			public int compare(File _f1, File _f2) {
				String name1 = _f1.getName();
				String name2 = _f2.getName();
				
				int num1 = new Integer(name1.substring(prefix.length(), name1.indexOf('.')));
				int num2 = new Integer(name2.substring(prefix.length(), name2.indexOf('.')));
				
				return num1-num2;
			}
		});
		
		int tmp = 2;
		for (File pic : pictures)
		{
			number++;
			if (tmp-- > 0)
				continue;
			
			p(pic.getName() + '\t' 
				+ "\\" + prefix + number + 
				pic.getName().substring(pic.getName().lastIndexOf('.')));
			
			File newFile = new File(path + "\\" + prefix + number + pic.getName().substring(pic.getName().lastIndexOf('.')));
			pic.renameTo(newFile);
			pic.delete();
		}
	}
	
	public static void chronologyRestore()
	{
		File rootDir = new File(path);
		File[] chronoDirs = rootDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File arg0) {
				return arg0.isDirectory();
			}
		});
		
		Arrays.sort(chronoDirs, new Comparator<File>(){
			@Override
			public int compare(File _f1, File _f2) {
				String name1 = _f1.getName();
				String name2 = _f2.getName();
				
				int num1 = name1.indexOf('_');
				int num2 = name2.indexOf('_');
				
				if (num1 == -1 &&
					num2 == -1)
				{
					return new Integer(name1) - new Integer(name2);
				}
				
				return new Integer((num1 != -1) ? 
							name1.substring(0, num1) + name1.substring(num1 + 1) : 
							(name1 + 0))
					- new Integer((num2 != -1) ? 
							name2.substring(0, num2) + name2.substring(num2 + 1) : 
							(name2 + 0));
			}
		});
		
		for (File dir : chronoDirs)
		{
			String dirName = dir.getName();
			File[] pictures = dir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File arg0, String arg1) {
					return arg1.endsWith(".jpg") ||
							arg1.endsWith(".JPG") ||
							arg1.endsWith(".3gp") ||
							arg1.endsWith(".3GP") ||
							arg1.endsWith(".mp4") ||
							arg1.endsWith(".MP4") ||
							arg1.endsWith(".avi") ||
							arg1.endsWith(".AVI");
				}
			});

			Arrays.sort(pictures, new Comparator<File>(){
				@Override
				public int compare(File _f1, File _f2) {
					String name1 = _f1.getName();
					String name2 = _f2.getName();
					return name1.compareTo(name2);
				}
			});
			
			for (File pic : pictures)
			{
				p(dirName + "\\" + pic.getName() + '\t' 
						+ "\\" + prefix + (number + 1) + 
						pic.getName().substring(pic.getName().lastIndexOf('.')));
				
				File newFile = new File(path + "\\" + prefix + ++number + pic.getName().substring(pic.getName().lastIndexOf('.')));
				pic.renameTo(newFile);
				pic.delete();
			}	
		}
	}
}
