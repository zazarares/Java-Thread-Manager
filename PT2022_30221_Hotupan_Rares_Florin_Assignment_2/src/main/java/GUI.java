import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI extends JFrame implements ActionListener{
	JFrame Frame=new JFrame("Queue Manager");
	JPanel panel=new JPanel();
	Button btn1=new Button("Add data");
	TextField N=new TextField();
	static TextField Q=new TextField();
	TextField tmax=new TextField();
	TextField clientparameters=new TextField();
	TextField clientparameters2=new TextField();
	TextField servicetime=new TextField();
	TextField servicetime2=new TextField();
	TextField Simtime=new TextField();
	JLabel Time=new JLabel();
	JLabel NL=new JLabel("N=");
	JLabel QL=new JLabel("Q=");
	JLabel clientparametersL=new JLabel("client parameters=");
	public boolean buttonpressed=false;
	ArrayList<JPanel> rectangle;
	ArrayList<JLabel> label;
	JLabel servicetimeL=new JLabel("service time=");
	GUI()
	{
		 Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 Frame.setSize(1100,850);
		 Frame.setLocationRelativeTo(null);
		 panel.setLayout(null);
		 btn1.setBounds(750,650,100,100);
		 btn1.setBackground(Color.RED);
		 N.setBounds(650,100,50,20);
		 Q.setBounds(700,100,50,20);
		 clientparameters.setBounds(750,100,75,20);
		 servicetime.setBounds(900,100,50,20);
		 clientparameters2.setBounds(825,100,75,20);
		 servicetime2.setBounds(950,100,50,20);
		 Simtime.setBounds(950,20,50,20);
		 NL.setBounds(650,80,50,20);
		 QL.setBounds(700,80,50,20);
		 clientparametersL.setBounds(750,80,150,20);
		 servicetimeL.setBounds(900,80,100,20);
		 panel.add(Simtime);
		 panel.add(btn1);
		 panel.add(N);
		 panel.add(Q);
		 panel.add(clientparameters);
		 panel.add(servicetime);
		 panel.add(clientparameters2);
		 panel.add(servicetime2);
		 panel.add(NL);
		 panel.add(QL);
		 panel.add(clientparametersL);
		 panel.add(servicetimeL);
		 
		 btn1.addActionListener(this);
		 Frame.setVisible(true);
		 Frame.setContentPane(panel);
		 
	}
	protected void generatequeues(int nrqueue)
	{
		int i=0,j=50;
		rectangle = new ArrayList<JPanel>(nrqueue);
		label = new ArrayList<JLabel>(nrqueue);
		while(i<nrqueue)
		{
			label.add(i,new JLabel("liber"));
			label.get(i).setBounds(j,(i%8)*100,70,70);
			panel.add(label.get(i));
			rectangle.add(i,new JPanel());
			rectangle.get(i).setBackground( Color.GREEN );
			rectangle.get(i).setBounds(j,(i%8)*100,70,70);
			panel.add( rectangle.get(i) );

			i++;
			if(i%8==0)
				j+=100;
			if(i>48)
				throw new RuntimeException("prea multe cozi!");
		}
		Frame.invalidate();
		Frame.validate();
		Frame.repaint();
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn1)
		{
			JLabel NL2=new JLabel("N=");
			JLabel QL2=new JLabel("Q=");
			JLabel clientparametersL2=new JLabel("client parameters=");
			JLabel servicetimeL2=new JLabel("service time=");
			 NL2.setBounds(650,180,50,20);
			 QL2.setBounds(650,200,50,20);
			 clientparametersL2.setBounds(650,220,150,20);
			 servicetimeL2.setBounds(650,240,100,20);
			 NL2.setText("N= "+N.getText());
			 QL2.setText("Q= "+Q.getText());
			 clientparametersL2.setText("clientparameters= "+clientparameters.getText());
			 servicetimeL2.setText("servicetime= "+servicetime.getText());
			 Time.setBounds(900,20,50,20);
			 panel.add(Time);
			 panel.add(NL2);
			 panel.add(QL2);
			 panel.add(clientparametersL2);
			 panel.add(servicetimeL2);
			 this.generatequeues(Integer.parseInt(Q.getText()));
			 btn1.setEnabled(false);
				Frame.invalidate();
				Frame.validate();
				Frame.repaint();
			Simulation S=new Simulation(this);
			Thread T =new Thread(S);
			T.start();
		}
	}
	protected int getClients()
	{
		return 	Integer.parseInt(Q.getText());
	}
}
