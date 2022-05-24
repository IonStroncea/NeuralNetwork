package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import faceRecognition.PhotoProcessing;
import faceRecognition.RecogNetwork;

public class FacePannel extends JPanel{
	JButton buttonOpen;
	JButton buttonSearch;
	JLabel l;
	boolean works;
	RecogNetwork network;
	
	public FacePannel(RecogNetwork network)
	{
		this.network = network;
		works=false;
		
		buttonOpen = new JButton("Open");
		
		buttonOpen.setBounds(100,1000,10,20);
		
		l = new JLabel("no file selected");
		
		buttonSearch=new JButton("Search faces");
		
		buttonOpen.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			            j.setAcceptAllFileFilterUsed(false);

			            j.setDialogTitle("Select a .jpg file");
			 
			            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .jpg files", "jpg");
			            j.addChoosableFileFilter(restrict);
			 
			            int r = j.showOpenDialog(null);
			 
			            // if the user selects a file
			            if (r == JFileChooser.APPROVE_OPTION) {
			                // set the label to the path of the selected file
			                l.setText(j.getSelectedFile().getAbsolutePath());
			            }
			            // if the user cancelled the operation
			            else
			                l.setText("the user cancelled the operation");	
					}

				});

		buttonSearch.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				Thread t1=new Thread(() -> {		
					try 
					{
						System.out.println("Entered");
						while(!network.ready)
						{
							
						}
						works=true;
						String path=l.getText();
						l.setText("Working...");
						PhotoProcessing.searchFaces(path, network);
						works=false;
						l.setText("Done");
					}
					catch(Exception error)
					{
						error.printStackTrace();
					}
				});
				
				t1.start();
			}
		});
		
		add(buttonOpen);
		add(l);
		add(buttonSearch);
	}
}
