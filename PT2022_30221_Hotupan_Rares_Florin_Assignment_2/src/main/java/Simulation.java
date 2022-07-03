import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Simulation implements Runnable{
		GUI G;
		int NrClienti;
		int NrCozi;
		int simtime;
		int MinArrival;
		int MaxArrival;
		int MinService;
		int MaxService;
		File log;
		FileWriter fw;
		ArrayList<persoana> P;
		public Simulation(GUI g)
		{
		G=g;
		log=new File("log.txt");
		try {
			fw=new FileWriter("log.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 NrClienti=Integer.parseInt(G.N.getText());
		NrCozi=Integer.parseInt(G.Q.getText());
		simtime=Integer.parseInt(G.Simtime.getText());
		MinArrival=Integer.parseInt(G.clientparameters.getText());
		MaxArrival=Integer.parseInt(G.clientparameters2.getText());
		MinService=Integer.parseInt(G.servicetime.getText());
		MaxService=Integer.parseInt(G.servicetime2.getText());
		P=new ArrayList<persoana>(NrClienti);
		for(int i=0;i<NrClienti;i++)
			{P.add(i,new persoana());P.get(i).generatetimes( MinService, MaxService,MinArrival, MaxArrival);}
		for(int i=0;i<NrClienti-1;i++)
			for(int j=i+1;j<NrClienti;j++)
				if(P.get(i).getarrival()>P.get(j).getarrival())
					Collections.swap(P,i,j);
		}
		private void addClients(ArrayList<Coada> C,int t)
		{
			for(int i=0;i<NrClienti;i++)
				if(P.get(i).getarrival()==t)
				{
					int min=10000;
					int mini=0;
					for(int j=0;j<NrCozi;j++)
						if(C.get(j).waitingtime<min)
							{min=C.get(j).waitingtime;mini=j;}
					C.get(mini).addelement(P.get(i));
					P.get(i).setarrival();
				}
		}
		private void writelog(ArrayList<Coada> C,int t)
		{
			try {
				fw.write("Time"+t+"\n");
				fw.write("waiting clients:");
				for(int i=0;i<NrClienti;i++)
				{
					if(P.get(i).getarrival()!=-1)
						fw.write("("+P.get(i).getindex()+","+P.get(i).getarrivalcopy()+","+P.get(i).getservicecopy()+");");
				}
				fw.write("\n");
				for(int j=0;j<NrCozi;j++)
				{
					fw.write("Queue"+C.get(j).index+" ");
				for(int i=0;i<NrClienti;i++)
				{
					if(C.get(j).q.contains(P.get(i)))
						{fw.write(":("+P.get(i).getindex()+","+P.get(i).getarrivalcopy()+","+P.get(i).getservicecopy()+");\n");}
					if(C.get(j).q.isEmpty())
						{fw.write("closed\n");break;}
				}
				}
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			ArrayList<Thread> T=new ArrayList<Thread>(NrCozi);
			ArrayList<Coada> C=new ArrayList<Coada>(NrCozi);
			for(int i=0;i<NrCozi;i++)
			{
				C.add(new Coada(G));
				T.add(new Thread(C.get(i)));
				T.get(i).start();
			}
			for(int t=1;t<simtime+1;t++)
			{
				addClients(C,t);
				writelog(C,t);
				G.Time.setText(""+t);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}
			}
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<NrCozi;i++)
			{
				C.get(i).terminate();
			}
		}

			// TODO Auto-generated method stub
			
		
}
