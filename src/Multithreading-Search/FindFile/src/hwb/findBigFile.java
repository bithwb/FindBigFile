package hwb;

import java.awt.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

public class findBigFile extends JFrame 
{
	private JLabel label_route;//·����ǩ
	private JLabel label_length1;//�趨��С��ǩ
	private JLabel label_length2;
	private JButton button_Open;//�趨·����ť
	private JTextField text_Route;//·��
	private JTextField text_length;//�趨��С
	private JButton button_Start;//��ʼ��ť
	private JButton button_Off;//ȡ����ť
	private JTable table;
	private  String routeString;
	private int n;
	private  long LENGTH;
	private Object[] outObjects = new Object[3];
	private File file;
	private static String regex=".*";// ������ʽ
	private boolean m;
	//private void 
	//private TextArea text_Info;
	/*
	 * ���캯��findBigFile();
	 */
	public findBigFile(){
		buildLayout();	
	}
	public static void main(String[] args) {
		new findBigFile();
	}
	
	/*
	 * ����������buildLayout() , ����һ������Container
	 */
	private void buildLayout(){
		final JFrame frame = new JFrame("���ļ�������");
		Container con = getContentPane();
		JPanel pane = new JPanel();
		JPanel pane1 = new JPanel();
		JPanel pane2 = new JPanel();
		pane.setLayout(new FlowLayout());
		pane1.setLayout(new FlowLayout());
		pane2.setLayout(new FlowLayout());
		GridLayout modelGridLayout = new GridLayout(2, 1) ;
		con.setLayout(modelGridLayout);
		//�趨���
		 Object[][] cellData = {{"","",""}};
	      String[] columnNames = {"�ļ���", "��С","·��"};
	      DefaultTableModel model = new DefaultTableModel(cellData, columnNames) {
	    	  public boolean isCellEditable(int row, int column) {
	    	  return false;
	    	  }
	    	  };
	     // table.setRowHeight(3,10);
	     // TableModel 
	     
	      table = new JTable(model);
	      JScrollPane jsp =new JScrollPane(table);
	       table.setPreferredScrollableViewportSize(new Dimension(450,150));
		label_route = new JLabel("�ļ�·����");
		label_length1 = new JLabel("��Ҫ���Ҵ���");
		label_length2 = new JLabel("MB���ļ�");
		button_Open = new JButton("Open...");
		button_Start = new JButton("Start Search");
		button_Off = new JButton("Off Search");
		text_Route = new JTextField("C:/",25);
		text_length = new JTextField(10);
		
		//�����¼������ڲ���
		button_Open.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
					ChooseToopen();
			}
		});
		//button_off����
		button_Off.addActionListener(new ActionListener() {
			//searcBigfile t;
			@Override
			public void actionPerformed(ActionEvent e) {
			    if(e.getActionCommand()==button_Off.getText()){
			    button_Start.setEnabled(true);//start��ť�ɵ�
			   // System.out.println(e.getActionCommand());
				Thread.currentThread().interrupt();
			    }
			}
			
		});
		//button_start����
		button_Start.addActionListener(new ActionListener() {
			searcHBigfile t;
			@Override
			public void actionPerformed(ActionEvent e) {
				button_Start.setEnabled(false);//start��ť���ɵ�
				DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
				tableModel.setRowCount(0);//���ԭ����
				table.invalidate();
				try{
				routeString = text_Route.getText();//ȡ��·��
				if(routeString=="")
				{routeString="C:/";}
				}catch(Exception e1){
					routeString="C:/";
					text_Route.setText(routeString);
				}//�쳣����
				try{
				n=Integer.parseInt(text_length.getText());
				}catch(Exception e2)
				{
					n=100;
					text_length.setText("100");
				}//�쳣����
				LENGTH=n*1024*1024L;
				//System.out.println("--"+routeString+"++");
				file = new File(routeString);
				//System.out.println("--------------------------------------------------");
				//search(file);
				t= new searcHBigfile(routeString, n);
//				Class c = Class.forName("searchBigfile");
//				Method method  = c.getDeclaredMethod("start");
//				method.setAccessible(true);
				t.start(routeString, n);
			   // button_Start.setEnabled(true);
				//outObjects=t.start(routeString,n);
				//System.out.println(routeString+n);
					//outObjects=t.run(routeString, n);
					//for(int i=0;i<3;i++)
					//{System.out.println(outObjects[i]+"�Ҳ��ҳ����");}
//				outObjects[0]="1";
//				outObjects[1]="2";
//				outObjects[2]="3";
				//tableModel.addRow(outObjects);
			    //table.invalidate();
				//button_Start.setEnabled(true);
				//outObjects=
			}
		  class searcHBigfile extends Thread{
				public String path;
				public File file;
				public File[] files;
				public long min;
				public Object[] arrStrings = new Object[3];
				public  searcHBigfile(String str,int max){
				path=str;
				file = new File(str);
				files = file.listFiles();
				min=max*1024*1024;
				}
			 public void start(String str,int j){
				     run(str,j);
			   }
			 public void run(String str, int m){
				 
				 boolean i;
				 Object[] brrStrings = new Object[3];
				// brrStrings[0]="1";brrStrings[1]="2";brrStrings[2]="3";
				 try{
				 if(this.files==null||this.files.length==0)
					 return ;
					// return brrStrings;
				 else{
					 for (File f:this.files) {
						  if(f.isFile()){
							 // this.sleep(1000);
							 //i = this.run(f.getAbsolutePath(),m);
							 if (f.length()>this.min) {
								brrStrings[0]=f.getName();
								brrStrings[1]=numberconverterString(f.length());
								brrStrings[2]=f.getPath();
								DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
								tableModel.addRow(brrStrings);
								table.invalidate();
								
								//System.out.println(f.getName()+"--��С--"+f.length()+"--·��--"+f.getPath());
								//return brrStrings;
							}
						  }
						  else {
							   searcHBigfile tBigfile = new searcHBigfile(f.getAbsolutePath(), m);
							   tBigfile.start(path, m);
							  
						}
					}
				 }
				 }catch(Exception e){
					 
				 }
			     //return brrStrings;
			   }
			}
			
		});
        //����
		pane.add(label_route);
		pane.add(text_Route);
		pane.add(button_Open);
		pane.add(label_length1);
		pane.add(text_length);
		pane.add(label_length2);
		pane.add(button_Start);
		pane.add(button_Off);
		pane1.add(jsp);
		//con.getPreferredSize();
		con.add(pane1);
		con.add(pane);
		frame.setContentPane(con);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(350, 150, 500, 400);
		frame.setVisible(true);
		frame.setResizable(false);
	}//����
	
	/*
	 * ����·��
	 */
	protected void ChooseToopen(){
		File file = ChooseFile();
		if(null==file||!file.exists())return;
		//System.out.println(file.getPath());
		//setPath(file.getPath());
	}
	private File ChooseFile(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = chooser.showOpenDialog(button_Open);
		if(result==JFileChooser.APPROVE_OPTION)
		{
			File file = chooser.getSelectedFile();
			showRoute(file.getAbsolutePath());
			//showInfo(file.getAbsolutePath());
		}else{
			JOptionPane.showMessageDialog(null, "��ûѡ���ļ��У�");
			return null;
		}
		return chooser.getSelectedFile();
	}
	protected void showRoute(String str) {
		text_Route.setText(str);
	}	

//�ݹ������ļ�
private  void search(File file) {
	File[] files = file.listFiles();
	if (files == null || files.length == 0)
		return;
	for (File f : files) {
		if (f.isFile()&&f.getName().matches(regex) ){
			if(f.length()>LENGTH){
				DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
				outObjects[0]=f.getName();
				outObjects[1]=numberconverterString(f.length());
				outObjects[2]=f.getPath();
				tableModel.addRow(outObjects);
				System.out.println(f.getName()+"--��С--"+f.length()+"--·��--"+f.getPath());
			}
			}
		else if(f.isDirectory()&&f.getName().matches(regex))
		   {
			getfileSizes th = new getfileSizes(f);
			if(th.start(f)>LENGTH){
			search(f);
			}
		}
	}
}
//������ת���ɿɶ��ĸ���
public String numberconverterString(long m) {
	long longValue = (long)m;

    float temp = 0;
    //����1k,С��1M
    if ((longValue >= 1024) && (longValue < 1024 * 1024))
    {
        temp = (float)longValue / 1024;
        return Float.toString(temp)+"K";
    }
    //����1M��С��1G
    if ((longValue >= 1024 * 1024) && (longValue < 1024 * 1024 * 1024))
    {
        temp = (float)longValue / (1024 * 1024);
        return Float.toString(temp) + "M";
    }
    //����1G
    if ((longValue >= 1024 * 1024 * 1024))
    {
        temp = (float)longValue / (1024 * 1024 * 1024);
         return Float.toString(temp) + "G";
    }
    //С��1K�ģ�������ʵ��С
    return Integer.toString((int)longValue)+"�ֽ�";
}

}
