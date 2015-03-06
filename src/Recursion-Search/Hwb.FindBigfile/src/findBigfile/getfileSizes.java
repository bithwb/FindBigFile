package findBigfile;

import java.io.File;

public class getfileSizes extends Thread {
  //private long size;
  private File file;
  private  String regex ;// 正则表达式
  public  getfileSizes(File f) {
	file = f;
	 regex=".*";
}
  public void run(){
	  
  }
  long start(File f){
	  long size = 0;
      File flist[] = f.listFiles();
      if(flist==null||flist.length==0)
    	  return 0;
      else{
      for (int i = 0; i < flist.length; i++)
      {
          if (flist[i].isDirectory()&&flist[i].getName().matches(regex))
          {   getfileSizes th1 = new getfileSizes(flist[i]);
              size = size + th1.start(flist[i]);
          } else
          {
              size = size + flist[i].length();
          }
      }
      return size; 
     }
  }
}
