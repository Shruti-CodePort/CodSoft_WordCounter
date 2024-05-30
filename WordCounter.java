import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
class WordCounter implements ActionListener
{
	JLabel l1;
	JButton b1,b2,b3;
	JTextField t1;
	JTextArea ta1;
	public WordCounter()
	{
		JFrame f=new JFrame();
		f.setVisible(true);
		f.setSize(400,400);
		f.setTitle("Word Counter");
		f.setLayout(null);

		l1=new JLabel("Enter Text or File Name for Counting the Words: ");
		l1.setBounds(40,30,350,30);
		f.add(l1);

		t1=new JTextField();
		t1.setBounds(40,70,300,30);
		f.add(t1);

		b1=new JButton("Upload File");
		b1.setBounds(40,110,140,30);
		f.add(b1);
		b1.addActionListener(this);
		
		b2=new JButton("Count The Words");
		b2.setBounds(200,110,140,30);
		f.add(b2);
		b2.addActionListener(this);

		ta1=new JTextArea();
		ta1.setBounds(40,150,300,40);
		f.add(ta1);

		b3=new JButton("Reset");
		b3.setBounds(40,200,100,30);
		f.add(b3);
		b3.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object source=ae.getSource();
		if(source==b1)
		{
			JFileChooser jf=new JFileChooser();
			int result=jf.showSaveDialog(null);
			if(result==JFileChooser.APPROVE_OPTION)
			{
				File selectFile=jf.getSelectedFile();
				t1.setText(selectFile.getAbsolutePath());
			}
			else
			{
				t1.setText("Cancelled The Operation");
			}
		}
		if(source==b2)
		{
			String userInput=t1.getText().trim();	
			if(userInput.isEmpty())
			{
				ta1.setText("No input Provided!");
				return;
			}
			
			int totalWordCount=0;

			try
			{
				String content=readInput(userInput);
				totalWordCount=wordsCounter(content);
				int commonWordCount=commonWords(content);
				ta1.setText("The Total Number of Words is: "+totalWordCount);
				ta1.append(" and Ignoring\n Common Words the number of Word is: "+commonWordCount);
			}	
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		if(source==b3)
		{
			ta1.setText("");
			t1.setText("");
		}
	}
	public static void main(String[] args)
	{
		WordCounter wc=new WordCounter();	
	}
	static String readInput(String userInput)throws IOException
	{
		if(userInput.contains("."))
		{
			String data;
			StringBuilder br=new StringBuilder();
			BufferedReader reader=new BufferedReader(new FileReader(userInput));
			while((data=reader.readLine())!=null)
			{
				br.append(data).append(" ");
			}
			reader.close();
			return br.toString();
		}
		else
		{
			return userInput;
		}
	}
	static int wordsCounter(String content)
	{
		String[] words=content.split(" ");
		int counter=0;
		for(String word:words)
		{
			counter++;
		}
		return counter;
	}
	static int commonWords(String content)
	{
		String words[]=content.split(" ");
		Set<String> unCommonWords=new HashSet<>();
		for(String word:words)
		{
			unCommonWords.add(word);
		}
		return unCommonWords.size();
	}
}