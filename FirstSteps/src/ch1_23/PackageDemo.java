package ch1_23;
public class PackageDemo {
	public static void qmain(String args[]){
	    Package pkgs[];

	    pkgs = Package.getPackages();

	    for(int i=0; i < pkgs.length; i++)
	      System.out.println(i + " " +
	             pkgs[i].getName() + " " +
	             pkgs[i].getImplementationTitle() + " " +
	             pkgs[i].getImplementationVendor() + " " +
	             pkgs[i].getImplementationVersion()
	      );
	}
}
