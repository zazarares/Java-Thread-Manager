import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.swing.*;
import java.awt.*;
public class Coada implements Runnable {

	BlockingQueue<persoana> q;
	boolean terminate;
	int waitingtime;
	GUI G;
	static int gindex=0;
	int index;
	persoana p;
	Coada(GUI G)
	{	
		this.G=G;
	index=gindex;
	gindex++;
	q=new ArrayBlockingQueue<persoana>(100);
	terminate=false;
	waitingtime=0;
	}
	protected void addelement(persoana p)
	{
		waitingtime+=p.getservice();
		q.add(p);
		
	}
	protected void terminate()
	{
		terminate=true;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
		while(!terminate)
		{while(!q.isEmpty())
		{
		p=q.peek();
		G.rectangle.get(index).setBackground(Color.RED);
		G.label.get(index).setText("clientul "+p.getindex());
		waitingtime-=1;
		G.Frame.invalidate();
		G.Frame.validate();
		G.Frame.repaint();
		p.setservice();
		if(p.getservice()==0)
			q.remove();
		Thread.sleep(1000);

		
		}
		while(q.isEmpty())
		{
			G.rectangle.get(index).setBackground(Color.GREEN);
			G.label.get(index).setText("liber");
			G.Frame.invalidate();
			G.Frame.validate();
			G.Frame.repaint();
			Thread.sleep(1000);
		}

		}
		}
		
		
		catch(InterruptedException e)
		{
			System.out.println("exception2");
		}
		
	}
	
}
