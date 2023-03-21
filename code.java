
package notepad;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.awt.GraphicsEnvironment.*;
import java.net.*;

public class Notepad extends JFrame implements ActionListener{

    JFrame frm,frame,frame3;
    JTextArea area;
    JScrollPane pane;
    String text,s1,s2,s3;
    JLabel status;
    JComboBox select;
    int n=20;
    Font fnt = new Font("Sens_Serif",Font.PLAIN,n),fnt1 = fnt;
    Notepad()
    {
        frm = new JFrame();
        frm.setBounds(0,0,1850,1000);
        
        JMenuBar mbar=new JMenuBar();
        
        JMenu file=new JMenu("FIle");
        
        JMenuItem New=new JMenuItem("New");
        New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        New.addActionListener(this);
        
        JMenuItem newwindow=new JMenuItem("NewWindow");
        newwindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.SHIFT_MASK|ActionEvent.CTRL_MASK));
        newwindow.addActionListener(this);
        
        JMenuItem open=new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        open.addActionListener(this);
        
        JMenuItem save=new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        save.addActionListener(this);
        
        JMenuItem saveas=new JMenuItem("SaveAs");
        saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.SHIFT_MASK|ActionEvent.CTRL_MASK));
        saveas.addActionListener(this);
        
        JMenuItem pagesetup=new JMenuItem("PageSetup");
        JMenuItem print=new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        print.addActionListener(this);
        JMenuItem exit=new JMenuItem("Exit");
        exit.addActionListener(this);
        
        file.add(New);
        file.add(newwindow);
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(pagesetup);
        file.add(print);
        file.add(exit);
       
        
        JMenu edit=new JMenu("Edit");
        
        JMenuItem undo=new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
        undo.addActionListener(this);
        JMenuItem cut=new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        cut.addActionListener(this);
        JMenuItem copy=new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        copy.addActionListener(this);
        JMenuItem paste=new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));        
        paste.addActionListener(this);
        JMenuItem delete=new JMenuItem("Delete");
        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
        delete.addActionListener(this);
        edit.add(undo);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(delete);
        
        
        JMenu format=new JMenu("Format");
        
        JMenuItem font=new JMenuItem("Font");
        font.addActionListener(this);
        
        format.add(font);
        
        
        JMenu help=new JMenu("Help");
        
        JMenuItem sendfeedback=new JMenuItem("Send Feedback");
        sendfeedback.addActionListener(this);
        
        help.add(sendfeedback);
        
        mbar.add(file);
        mbar.add(edit);
        mbar.add(format);
        mbar.add(help);
        
        
        frm.setJMenuBar(mbar);
        
        area = new JTextArea();
        area.setFont(fnt);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        
        
        pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        frm.add(pane, BorderLayout.CENTER);
        frm.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("New")){
            area.setText("");
        }
        else if(ae.getActionCommand().equals("NewWindow")){
            new Notepad().setVisible(true);
        }
        else if(ae.getActionCommand().equals("Open")){
           
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("only.txt files","txt");
            chooser.addChoosableFileFilter(restrict);
            
            
            int action = chooser.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            
            File file = chooser.getSelectedFile();
            try{
                
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            } catch(Exception e){}
        }
        else if(ae.getActionCommand().equals("Save")){
            JFileChooser save1 = new JFileChooser();
            save1.setApproveButtonText("Save");
            int action =save1.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            
            
            File filename = new File(save1.getSelectedFile()+".txt");
            BufferedWriter outfile = null;
            try{
                outfile = new BufferedWriter(new FileWriter(filename));
                area.write(outfile);
            }catch(Exception e){}
        }
        
        else if(ae.getActionCommand().equals("Print")){
  
            try{
                area.print();
            } catch(Exception e){}
        }
        else if(ae.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        else if(ae.getActionCommand().equals("Copy")){
            text = area.getSelectedText();
        }
        else if(ae.getActionCommand().equals("Paste")){
            area.insert(text, area.getCaretPosition());
        }
        else if(ae.getActionCommand().equals("Cut")){
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }
        else if(ae.getActionCommand().equals("Font")){
          
            frm.setEnabled(false);
            JList l1,l2,l3;
            JLabel label,label2,label3;
            JScrollPane scroll,scroll1,scroll2;
            JTextField field1,field2,field3;
            JButton ok,cancel;
            int n=20;
            
            frame = new JFrame("Font");
            frame.setBounds(0,100,450,500);
            frame.setLayout(null);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            String[] fn = ge.getAvailableFontFamilyNames();
            
            l1 = new JList(fn);  
            l1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            scroll = new JScrollPane(l1);
            scroll.setPreferredSize(new Dimension(150,200));
            scroll.setBounds(15,80,150,150);
            

            String[] fontstyle = {"Regular", "<html><i>Italic</i></html>","<html><b>Bold</b></html>", "<html><i><b>Bold Italic</b></i></html>"};
            l2 = new JList(fontstyle);
            l2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);            
            
            scroll1 = new JScrollPane(l2);
            scroll1.setPreferredSize(new Dimension(150,200));
            scroll1.setBounds(180,80,100,150);
            
            String[] size =new String[100]; 
            for( int i = 1;i<=80;i++){
                size[i] = String.valueOf(i);
                
            }
            l3 = new JList(size);
            l3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            scroll2 = new JScrollPane(l3);
            scroll2.setPreferredSize(new Dimension(150,200));
            scroll2.setBounds(280, 80, 100, 150); int m;
            
            field1 = new JTextField();
            field2 = new JTextField();
            field3 = new JTextField();            
            l1.addListSelectionListener(new ListSelectionListener(){    
                public void valueChanged(ListSelectionEvent le){
                    int idx = l1.getSelectedIndex();
                    if(idx != -1){
                        field1.setText(fn[idx]);
                        s1 = fn[idx];
                    }
                }
            });  fnt1 = new Font(s1,Font.PLAIN,n);
             l2.addListSelectionListener(new ListSelectionListener(){
                
                public void valueChanged(ListSelectionEvent le){
                    int idx = l2.getSelectedIndex();
                    if(idx != -1){
                        field2.setText(fontstyle[idx]);
                        s2 = fontstyle[idx];
                    }
                }
            });
             l3.addListSelectionListener(new ListSelectionListener(){
                
                public void valueChanged(ListSelectionEvent le){
                    int idx = l3.getSelectedIndex();
                    if(idx != -1){
                        field3.setText(size[idx]);
                        s3 = size[idx];
                    }
                }
            });
            n= 90;
            if(s2=="Regular") fnt1 = new Font(s1,Font.PLAIN,n);
                        else if(s2=="<html><i>Italic</i></html>") fnt1 = new Font(s1,Font.ITALIC,n);
                        else if(s2=="<html><b>Bold</b></html>")   fnt1 = new Font(s1,Font.BOLD,n);
                        else if(s2=="<html><i><b>Bold</b></i></html>") fnt1 = new Font(s1,Font.BOLD|Font.ITALIC,n);
             
            label = new JLabel("Font :");
            label.setBounds(15,30,240,50);
            label2 = new JLabel("Font Style :");
            label2.setBounds(180,30,240,50);
            label3 = new JLabel("Size :");
            label3.setBounds(280, 30, 240, 50);
            
            field1.setBounds(15, 60, 100, 20);
            field3.setBounds(280, 60, 100, 20);

            ok = new JButton("OK"); ok.setFont(new Font("Sens_Serif",Font.BOLD,10));
            ok.addActionListener(this);
            cancel = new JButton("Cancel"); cancel.setFont(new Font("Sens_Serif",Font.BOLD,10));
            cancel.addActionListener(this);
            ok.setBounds(200,400,50,50);
            cancel.setBounds(260,400,80,50);
         
            frame.add(label);
            frame.add(scroll);
            frame.add(field1);
            frame.add(label2);
            frame.add(scroll1);
            frame.add(label3);
            frame.add(field3);
            frame.add(scroll2);
            frame.add(ok);
            frame.add(cancel);
            frame.setVisible(true);
            frame.setResizable(false);

            
        }
        else if(ae.getActionCommand().equals("OK")){
           area.setFont(fnt1);
          
            
            frame.setVisible(false);
            frm.setEnabled(true);
            frm.setVisible(true);
        }
        else if(ae.getActionCommand().equals("Cancel")){
            frame.setVisible(false);
            frame3.setVisible(false);
            frm.setEnabled(true);
            frm.setVisible(true);
        }
        else if(ae.getActionCommand().equals("Send Feedback")){
            frm.setEnabled(false);
            frame3 = new JFrame("Feedback");
            frame3.setVisible(true);
            frame3.setBounds(50,50,600,700);
            frame3.setResizable(false);
            frame3.setLayout(null);
            JLabel l4 = new JLabel("Enter your Feedback");
            l4.setFont(new Font("Osward",Font.BOLD,25));
            l4.setBounds(100,50,450,100);
            JTextField tf1 = new JTextField(50);
            tf1.setFont(new Font("Arial",Font.BOLD,14));
            tf1.setBounds(100,170,400,200);
            
            
            frame3.add(tf1);
            frame3.add(l4);
            JButton Submit,cancel;
            
            Submit = new JButton("Submit"); Submit.setFont(new Font("Sens_Serif",Font.BOLD,15));
            Submit.addActionListener(this);
            cancel = new JButton("Cancel"); cancel.setFont(new Font("Sens_Serif",Font.BOLD,15));
            cancel.addActionListener(this);
            Submit.setBounds(100,400,100,50);
            cancel.setBounds(300,400,100,50);
            
            frame3.add(Submit);
            frame3.add(cancel);
        }
        else if(ae.getActionCommand().equals("Submit")){
            frame3.setVisible(false);
            frm.setEnabled(true);
            
        }
       
           
}
         
   
    public static void main(String[] args) {
        
        new Notepad();
    }
    
    
}
