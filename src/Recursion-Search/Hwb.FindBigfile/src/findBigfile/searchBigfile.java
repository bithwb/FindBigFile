package findBigfile;

import java.io.File;
public class searchBigfile extends Thread{
	private String path;
	private File file;
	private File[] files;
	private long min;
	private Object[] arrStrings = new Object[3];
	public  searchBigfile(String str,int max){
	path=str;
	file = new File(str);
	files = file.listFiles();
	min=max*1024*1024;
	}
   public boolean run(String str,int j){
	File newfile = new File(str);
	if(newfile.length()>this.min)
	 return true;
	else {
	  return false;
	}
   }
 Object[] start(String str, int m){
	 boolean i;
	 searchBigfile tt = new searchBigfile(path, m);
	 System.out.println(str);
	 Object[] brrStrings = new Object[3];
	// brrStrings[0]="1";brrStrings[1]="2";brrStrings[2]="3";
	 try{
	 if(tt.files==null||tt.files.length==0)
		 return brrStrings;
	 else{
		 for (File f:this.files) {
			  if(f.isFile()){
				 // this.sleep(1000);
				 i = this.run(f.getAbsolutePath(),m);
				 if (i) {
					brrStrings[0]=f.getName();
					brrStrings[1]=f.length();
					brrStrings[2]=f.getPath();
					return brrStrings;
				}
			  }
			  else {
				searchBigfile tBigfile = new searchBigfile(f.getAbsolutePath(), m);
				brrStrings = tBigfile.start(path, m);
			}
		}
	 }
	 }catch(Exception e){
		 
	 }
     return brrStrings;
   }
}