/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

/**
 *
 * @author yiqian
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import memory.Memory;
import memory.MCU;
import cpu.CPU;
import util.Const;
import util.Program1;
import instruction.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import memory.Cache;
import util.MachineFaultException;
import util.StringUtil;
import util.Program2;
import util.ProgramFixed2Float;
import util.ProgramFloat2Fixed;
import util.ProgramFloatAdd;
import util.ProgramFloatSub;

public class ControlPanel extends JFrame{
   
   private JFrame frame;
   
   private JPanel Global;
   
   private JPanel panel_console_print,panel_console_keyboard, panel_console_cache,panel_leftbot,panel_register,panel_instruction, panel_memory,panel_mfranddeposit,panel_deposit,panel_control,panel_consoletext,panel_clearbutton,panel_left,panel_Pc,panel_Mfr,panel_Ir,panel_lastline,panel_console,Registerset0,Registerset1,Registerset2,Registerset3,Index_Reg1_set,Index_Reg2_set,Index_Reg3_set,Mar_set,Mbr_set;
   
   private JLabel[] Pc,Cc,Mfr,Ir,Register1,Register2,Register3,Register0,Index_Reg1,Index_Reg2,Index_Reg3,Mar,Mbr;
   
   private JLabel label_program1,label_program2, label_instruction, label_Pc,label_Cc,label_Mfr,label_Ir,label_opcode,label_PC,label_CC, label_R1,label_R0,label_R2,label_R3,label_IX_R1,label_IX_R2,label_IX_R3,label_Mar,label_Mbr,label_Address;
   
   private JLabel label_console_printer, label_console_keyboard,label_console_cache,label_Pc_val,label_Ir_val,label_R1_value,label_R0_value,label_R2_value,label_R3_value,label_CC_val, label_PC_val,label_opcode_val,
                    label_IX_R1_val,label_IX_R2_val,label_IX_R3_val,label_Mar_val,label_Mbr_val,label_Value;
   
   private JTextField text_Pc,text_Ir,text_R1,text_R0,text_R2,text_R3,text_IX_R1,text_IX_R2,text_IX_R3,text_Mar,text_Mbr,text_Address,text_Val;
   
   private JTextArea text_console_print, text_console_keyboard;
   private JTable text_console_cache;
   private JScrollPane scrollPane_cache, scrollPane_console_printer;
   
   private JButton button_load,button_find, button_compare,button_read20number,button_p1,button_p2,button_enter,button_run,button_halt,button_deposit,button_singlestep,button_console,button_memory,button_IPL;
   private JRadioButton[] instruction;
   
   private String getTextPC, getTextR0, getTextR1, getTextR2, getTextR3, getTextX1, getTextX2, getTextX3, getTextMAR, getTextMBR, getTextIR;
   private String getKey, getValue;
   
   public String ConsoleString = "";
   
   public int PCstore;
   Memory mainMemorystore;
   
   boolean Single = false, Continue = true;
   
   Memory mainMemory;
   CPU cpu;
   MCU mcu;
   private int program1Step, program2Step;
   
   private int PC;

   private int devid = 0;
   
   // Save situation when halt
   int tempPC;
   String tempIR;

   private JButton FloatAdd_Button, VectorAdd_Button, FloatConver_Button, VectorSub_Button;
   private JLabel P4space1, P4space2;


    private void initComponents()
    {
        this.frame = new JFrame("part4");
        this.frame.setLayout(new BorderLayout(10,10));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocation(500,50);
        this.frame.setResizable(true);
        this.panel_register=new JPanel(new GridLayout(12,1));
        this.panel_left=new JPanel(new BorderLayout());
        this.panel_leftbot=new JPanel(new GridLayout(3,1));
        
       
        /**
         * PC ===============================================
         */
        this.panel_Pc=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
        this.Pc=new JLabel[20];
        this.label_Pc=new JLabel("PC");
        this.label_Pc.setPreferredSize(new Dimension(30,10));
        this.label_Pc_val=new JLabel("val");
        this.text_Pc=new JTextField();
        this.text_Pc.setPreferredSize(new Dimension(80,30));
             
        this.panel_Pc.add(this.label_Pc);
        for(int i=0; i<16;i++)
        {
            this.Pc[i]=new JLabel("");
            this.Pc[i].setPreferredSize(new Dimension(10,10));
            if(i<12)
            this.Pc[i].setOpaque(true);
            this.Pc[i].setBackground(Color.white);
        }

        for(int i=0;i<16;i++)
            this.panel_Pc.add(this.Pc[i]);       
        this.panel_Pc.add(this.label_Pc_val);
        this.panel_Pc.add(this.text_Pc);
        this.panel_register.add(this.panel_Pc);


        /**
         * R0 =======================================================
         */
        this.Registerset0=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
        this.Register0=new JLabel[20];
        this.label_R0=new JLabel("R0");
        this.label_R0.setPreferredSize(new Dimension(30,10));
        this.label_R0_value=new JLabel("val");
        this.text_R0=new JTextField();
        this.text_R0.setPreferredSize(new Dimension(80,30));       
        this.Registerset0.add(this.label_R0);
        for(int i=0; i<16;i++)
        {
            this.Register0[i]=new JLabel("");
            this.Register0[i].setPreferredSize(new Dimension(10,10));
            this.Register0[i].setOpaque(true);
            this.Register0[i].setBackground(Color.white);
        }
        for(int i=0;i<16;i++)
            this.Registerset0.add(this.Register0[i]);       
        this.Registerset0.add(this.label_R0_value);
        this.Registerset0.add(this.text_R0);
        this.panel_register.add(this.Registerset0);



        /**
         * R1 =======================================================
         */
        this.Registerset1=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
        this.Register1=new JLabel[20];
        this.label_R1=new JLabel("R1");
        this.label_R1.setPreferredSize(new Dimension(30,10));
        this.label_R1_value=new JLabel("val");
        this.text_R1=new JTextField();
        this.text_R1.setPreferredSize(new Dimension(80,30));       
        this.Registerset1.add(this.label_R1);
        for(int i=0; i<16;i++)
        {
            this.Register1[i]=new JLabel("");
            this.Register1[i].setPreferredSize(new Dimension(10,10));
            this.Register1[i].setOpaque(true);
            this.Register1[i].setBackground(Color.white);
        }
        for(int i=0;i<16;i++)
            this.Registerset1.add(this.Register1[i]);       
        this.Registerset1.add(this.label_R1_value);
        this.Registerset1.add(this.text_R1);
        this.panel_register.add(this.Registerset1);


         /**
          * R2 ========================================================
          */
        this.Registerset2=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
        this.Register2=new JLabel[20];
        this.label_R2=new JLabel("R2");
        this.label_R2.setPreferredSize(new Dimension(30,10));
        this.label_R2_value=new JLabel("val");
        this.text_R2=new JTextField();
        this.text_R2.setPreferredSize(new Dimension(80,30));           
        this.Registerset2.add(this.label_R2);
        for(int i=0; i<16;i++)
        {
            this.Register2[i]=new JLabel("");
            this.Register2[i].setPreferredSize(new Dimension(10,10));
            this.Register2[i].setOpaque(true);
            this.Register2[i].setBackground(Color.white);
        }
        for(int i=0;i<16;i++)
            this.Registerset2.add(this.Register2[i]);       
        this.Registerset2.add(this.label_R2_value);
        this.Registerset2.add(this.text_R2);        
        this.panel_register.add(this.Registerset2);

         /**
          * R3 ========================================================
          */
        this.Registerset3=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
        this.Register3=new JLabel[20];
        this.label_R3=new JLabel("R3");
        this.label_R3.setPreferredSize(new Dimension(30,10));
        this.label_R3_value=new JLabel("val");
        this.text_R3=new JTextField();
        this.text_R3.setPreferredSize(new Dimension(80,30));              
        this.Registerset3.add(this.label_R3);
        for(int i=0; i<16;i++)
        {
            this.Register3[i]=new JLabel("");
            this.Register3[i].setPreferredSize(new Dimension(10,10));
            this.Register3[i].setOpaque(true);
            this.Register3[i].setBackground(Color.white);
        }
        for(int i=0;i<16;i++)
            this.Registerset3.add(this.Register3[i]);       
        this.Registerset3.add(this.label_R3_value);
        this.Registerset3.add(this.text_R3);       
        this.panel_register.add(this.Registerset3);

         /**
          * X1 ========================================================
          */
        this.Index_Reg1_set=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
        this.Index_Reg1=new JLabel[16];
        this.label_IX_R1=new JLabel("X1");
        this.label_IX_R1.setPreferredSize(new Dimension(30,10));
        this.label_IX_R1_val=new JLabel("val");
        this.text_IX_R1=new JTextField();
        this.text_IX_R1.setPreferredSize(new Dimension(80,30));               
        this.Index_Reg1_set.add(this.label_IX_R1);
        for(int i=0; i<16;i++)
        {
            this.Index_Reg1[i]=new JLabel("");
            this.Index_Reg1[i].setPreferredSize(new Dimension(10,10));
            this.Index_Reg1[i].setOpaque(true);
            this.Index_Reg1[i].setBackground(Color.white);
        }
        for(int i=0;i<16;i++)
            this.Index_Reg1_set.add(this.Index_Reg1[i]);       
        this.Index_Reg1_set.add(this.label_IX_R1_val);
        this.Index_Reg1_set.add(this.text_IX_R1);       
        this.panel_register.add(this.Index_Reg1_set);


         /**
          * X2 ========================================================
          */
        this.Index_Reg2_set=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
        this.Index_Reg2=new JLabel[16];
        this.label_IX_R2=new JLabel("X2");
        this.label_IX_R2.setPreferredSize(new Dimension(30,10));
        this.label_IX_R2_val=new JLabel("val");
        this.text_IX_R2=new JTextField();
        this.text_IX_R2.setPreferredSize(new Dimension(80,30));         
        this.Index_Reg2_set.add(this.label_IX_R2);
        for(int i=0; i<16;i++)
        {
            this.Index_Reg2[i]=new JLabel("");
            this.Index_Reg2[i].setPreferredSize(new Dimension(10,10));
            this.Index_Reg2[i].setOpaque(true);
            this.Index_Reg2[i].setBackground(Color.white);
        }
        for(int i=0;i<16;i++)
            this.Index_Reg2_set.add(this.Index_Reg2[i]);       
        this.Index_Reg2_set.add(this.label_IX_R2_val);
        this.Index_Reg2_set.add(this.text_IX_R2);        
        this.panel_register.add(this.Index_Reg2_set);

         /**
          * X3 ========================================================
          */
        this.Index_Reg3_set=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
        this.Index_Reg3=new JLabel[16];
        this.label_IX_R3=new JLabel("X3");
        this.label_IX_R3.setPreferredSize(new Dimension(30,10));
        this.label_IX_R3_val=new JLabel("val");
        this.text_IX_R3=new JTextField();
        this.text_IX_R3.setPreferredSize(new Dimension(80,30));              
        this.Index_Reg3_set.add(this.label_IX_R3);
        for(int i=0; i<16;i++)
        {
            this.Index_Reg3[i]=new JLabel("");
            this.Index_Reg3[i].setPreferredSize(new Dimension(10,10));
            this.Index_Reg3[i].setOpaque(true);
            this.Index_Reg3[i].setBackground(Color.white);
        }
        for(int i=0;i<16;i++)
            this.Index_Reg3_set.add(this.Index_Reg3[i]);       
        this.Index_Reg3_set.add(this.label_IX_R3_val);
        this.Index_Reg3_set.add(this.text_IX_R3);        
        this.panel_register.add(this.Index_Reg3_set);

        
         /**
          * MAR ========================================================
          */
        this.Mar_set=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
        this.Mar=new JLabel[20];
        this.label_Mar=new JLabel("MAR");
        this.label_Mar.setPreferredSize(new Dimension(30,10));
        this.label_Mar_val=new JLabel("val");
        this.text_Mar=new JTextField();
        this.text_Mar.setPreferredSize(new Dimension(80,30));
        this.Mar_set.add(label_Mar);
        for(int i=0; i<16;i++)
        {
            this.Mar[i]=new JLabel("");
            this.Mar[i].setPreferredSize(new Dimension(10,10));
            this.Mar[i].setOpaque(true);
            this.Mar[i].setBackground(Color.white);
        }
        for(int i=0;i<16;i++)
            this.Mar_set.add(this.Mar[i]);       
        this.Mar_set.add(this.label_Mar_val);
        this.Mar_set.add(this.text_Mar);
        this.panel_register.add(this.Mar_set);



         /**
          * MBR ========================================================
          */
        this.Mbr_set=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
        this.Mbr=new JLabel[20];
        this.label_Mbr=new JLabel("MBR");
        this.label_Mbr.setPreferredSize(new Dimension(30,10));
        this.label_Mbr_val=new JLabel("val");
        this.text_Mbr=new JTextField();
        this.text_Mbr.setPreferredSize(new Dimension(80,30));          
        this.Mbr_set.add(this.label_Mbr);
        for(int i=0; i<16;i++)
        {
            this.Mbr[i]=new JLabel("");
            this.Mbr[i].setPreferredSize(new Dimension(10,10));
            this.Mbr[i].setOpaque(true);
            this.Mbr[i].setBackground(Color.white);
        }
        for(int i=0;i<16;i++)
            this.Mbr_set.add(this.Mbr[i]);       
        this.Mbr_set.add(this.label_Mbr_val);
        this.Mbr_set.add(this.text_Mbr);        
        this.panel_register.add(this.Mbr_set);

        
        
         /**
          * IR ========================================================
          */
        this.panel_Ir=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
        this.Ir=new JLabel[20];
        this.label_Ir=new JLabel("IR");
        this.label_Ir.setPreferredSize(new Dimension(30,10));
        this.label_Ir_val=new JLabel("val");
        this.text_Ir=new JTextField();
        this.text_Ir.setPreferredSize(new Dimension(80,30));            
        this.panel_Ir.add(this.label_Ir);
        for(int i=0; i<16;i++)
        {
            this.Ir[i]=new JLabel("");
            this.Ir[i].setPreferredSize(new Dimension(10,10));
            this.Ir[i].setOpaque(true);
            this.Ir[i].setBackground(Color.white);
        }
        for(int i=0;i<16;i++)
            this.panel_Ir.add(this.Ir[i]);       
        this.panel_Ir.add(this.label_Ir_val);
        this.panel_Ir.add(this.text_Ir);        
        this.panel_register.add(this.panel_Ir);


         /**
          * MFR CC ========================================================
          */
        this.panel_Mfr=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
        this.panel_deposit=new JPanel(new FlowLayout(FlowLayout.RIGHT,30,10));
        this.panel_mfranddeposit=new JPanel(new BorderLayout());
        
        this.button_deposit=new JButton("Deposit Reg");
        this.button_deposit.setPreferredSize(new Dimension(130, 45));
        this.Mfr=new JLabel[20];
        this.Cc=new JLabel[5];
        this.label_Cc=new JLabel("CC");
        this.label_Cc.setPreferredSize(new Dimension(20,10));
        this.label_Mfr=new JLabel("MFR");
        this.label_Mfr.setPreferredSize(new Dimension(30,10));    
        this.panel_Mfr.add(this.label_Mfr);
        
        for(int i=0; i<15;i++)
        {
            this.Mfr[i]=new JLabel("");
            this.Mfr[i].setPreferredSize(new Dimension(10,10));
            if(i<5)
            this.Mfr[i].setOpaque(true);
            this.Mfr[i].setBackground(Color.white);
        }
        for(int i=0;i<4;i++)
            this.panel_Mfr.add(this.Mfr[i]);   

        this.panel_Mfr.add(this.label_Cc);
        for(int i=0; i<4;i++)
        {
            this.Cc[i]=new JLabel("");
            this.Cc[i].setPreferredSize(new Dimension(10,10));
            this.Cc[i].setOpaque(true);
            this.Cc[i].setBackground(Color.white);
        }
        for(int i=0;i<4;i++)
            this.panel_Mfr.add(this.Cc[i]);         
        
        this.panel_deposit.add(this.button_deposit);
        /**
         * deposit number in Regs
         */
        this.button_deposit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTextPC = text_Pc.getText();
                getTextR0 = text_R0.getText();
                getTextR1 = text_R1.getText();
                getTextR2 = text_R2.getText();
                getTextR3 = text_R3.getText();
                getTextX1 = text_IX_R1.getText();
                getTextX2 = text_IX_R2.getText();    
                getTextX3 = text_IX_R3.getText();
                getTextMAR = text_Mar.getText();
                getTextMBR = text_Mbr.getText();
                getTextIR = text_Ir.getText();
                
                ShowNumberR(getTextR0, getTextR1, getTextR2, getTextR3, cpu.getRnByNum(0), cpu.getRnByNum(1), cpu.getRnByNum(2), cpu.getRnByNum(3), false);
                ShowNumberX(getTextX1, getTextX2, getTextX3, cpu.getXnByNum(0), cpu.getXnByNum(1), cpu.getXnByNum(2), false);
                ShowNumberO(getTextPC, getTextMAR, getTextMBR, getTextIR, cpu.getPC(), cpu.getMAR(), cpu.getMBR(), cpu.getIR(), false);
                Continue = false;
            }                        
        });
        
        this.panel_mfranddeposit.add(this.panel_Mfr,BorderLayout.CENTER);
        this.panel_mfranddeposit.add(this.panel_deposit,BorderLayout.EAST);      
        this.panel_register.add(this.panel_mfranddeposit);
        
        //panel instruction
        this.panel_instruction=new JPanel();
        this.panel_instruction.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        
        this.label_instruction=new JLabel("Float/Vector Test:");
        this.label_instruction.setPreferredSize(new Dimension(120,10));
        this.panel_instruction.add(this.label_instruction);
        
        this.FloatAdd_Button = new JButton("Float Add/Sub");
        this.FloatAdd_Button.setPreferredSize(new Dimension(140,50));
        this.panel_instruction.add(this.FloatAdd_Button);
        
        this.FloatConver_Button = new JButton("Float Conver");
        this.FloatConver_Button.setPreferredSize(new Dimension(140,50));
        this.panel_instruction.add(this.FloatConver_Button);
        
        this.VectorAdd_Button = new JButton("Vector Add");
        this.VectorAdd_Button.setPreferredSize(new Dimension(140,50));
        this.panel_instruction.add(this.VectorAdd_Button);   
        
        this.VectorSub_Button = new JButton("Vector Sub");
        this.VectorSub_Button.setPreferredSize(new Dimension(140,50));
        this.panel_instruction.add(this.VectorSub_Button);   
        this.panel_leftbot.add(this.panel_instruction);        
        
        this.FloatAdd_Button.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                    // read 20 number from console
                        mcu.setKeyboardBuffer(text_console_keyboard.getText());
                        if (text_console_keyboard.getText()==null || text_console_keyboard.getText().length()==0){
                            JOptionPane.showMessageDialog(null, "type numbers in the console keyboard");
                        }else{
                            String[] temp_string = text_console_keyboard.getText().split(" ");
                            float num1 = Float.parseFloat(temp_string[0]);
                            float result=0;
                            String op="+";
                            String add = "+";
                            String sub = "-";
                            DecimalFormat fnum = new DecimalFormat("##0.000");
                            for(String temp: temp_string){
                                printConsole(temp);
                                if(temp.equals(add)){
                                    op = add;
                                }else if(temp.equals(sub)){
                                    op = sub;
                                }else{
                                    if(op.equals(add)){
                                        mcu.loadProgram(ProgramFloatAdd.StoreFloat);
                                        mcu.loadProgram(ProgramFloatAdd.Read_FloatAdd);
                                    }else{
                                        mcu.loadProgram(ProgramFloatSub.StoreFloat);
                                        mcu.loadProgram(ProgramFloatSub.Read_FloatSub);
                                    }
                                }
                            }
                            printConsole("the result is " + fnum.format(result));
                            refreshPanel();
                    
                }    
            }
        });
        
        this.FloatConver_Button.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                    // read 20 number from console
                        mcu.setKeyboardBuffer(text_console_keyboard.getText());
                        if (text_console_keyboard.getText()==null || text_console_keyboard.getText().length()==0){
                            JOptionPane.showMessageDialog(null, "type numbers in the console keyboard");
                        }else{
                            String[] temp_string = text_console_keyboard.getText().split(" ");
                            float num1 = Float.parseFloat(temp_string[0]);
                            float result1=0;
                            int result2 = 0;
                            String op=temp_string[2];
                            String fixed_num = "0";
                            DecimalFormat fnum = new DecimalFormat("##0.00000000");
                            
                            if(op.equals(fixed_num)){
                                mcu.loadProgram(ProgramFloat2Fixed.StoreFloat);
                                mcu.loadProgram(ProgramFloat2Fixed.Float2Fixed);
                            }else{
                                mcu.loadProgram(ProgramFixed2Float.StoreFloat);
                                mcu.loadProgram(ProgramFixed2Float.Float2Fixed);
                            }
                            printConsole("the result is " + result1 + " exp is " + result2);
                            refreshPanel();
                    
                }    
            }
        });
        
         this.VectorAdd_Button.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                    // read 20 number from console
                        mcu.setKeyboardBuffer(text_console_keyboard.getText());
                        if (text_console_keyboard.getText()==null || text_console_keyboard.getText().length()==0){
                            JOptionPane.showMessageDialog(null, "type numbers in the console keyboard");
                        }else{
                            String[] temp_string = text_console_keyboard.getText().split(" ");
                            int num1 = Integer.parseInt(temp_string[0]);
                            int result=0;
                            for(String temp: temp_string){
                                printConsole(temp);
                                result += Integer.parseInt(temp);
                            }
                            printConsole("the result is " + result);
                            refreshPanel();
                    
                }    
            }
        }); 
         
          this.VectorSub_Button.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                    // read 20 number from console
                        mcu.setKeyboardBuffer(text_console_keyboard.getText());
                        if (text_console_keyboard.getText()==null || text_console_keyboard.getText().length()==0){
                            JOptionPane.showMessageDialog(null, "type numbers in the console keyboard");
                        }else{
                            String[] temp_string = text_console_keyboard.getText().split(" ");
                            int num1 = Integer.parseInt(temp_string[0]);
                            int result=0;
                            for(String temp: temp_string){
                                printConsole(temp);
                                result -= Integer.parseInt(temp);
                            }
                            result += 2*Integer.parseInt(temp_string[0]);
                            printConsole("the result is " + result);
                            refreshPanel();
                    
                }    
            }
        });         
        
            
       //panel memory
        this.panel_memory=new JPanel(null);
        this.panel_memory.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        
        this.label_program1=new JLabel("Program 1: ");
        this.label_program1.setPreferredSize(new Dimension(120,50));
        this.label_program1.setHorizontalAlignment(JLabel.RIGHT);
        this.panel_memory.add(label_program1);
        
        this.button_read20number=new JButton("Read");
        this.button_read20number.setPreferredSize(new Dimension(140, 50));
        this.panel_memory.add(button_read20number);
        
        this.button_compare=new JButton("Compare");
        this.button_compare.setPreferredSize(new Dimension(140, 50));
        this.panel_memory.add(button_compare);
        
        this.label_program2=new JLabel("Program 2: ");
        this.label_program2.setPreferredSize(new Dimension(120,50));
        this.label_program2.setHorizontalAlignment(JLabel.RIGHT);
        this.panel_memory.add(label_program2);
        
        this.button_load=new JButton("Load");
        this.button_load.setPreferredSize(new Dimension(140, 50));
        this.panel_memory.add(button_load);
        
        this.button_find=new JButton("Find");
        this.button_find.setPreferredSize(new Dimension(140, 50));
        this.panel_memory.add(button_find);
        
        this.panel_memory.setPreferredSize(new Dimension(30, 1500));
        this.panel_leftbot.add(this.panel_memory);
        
         /**
          * Read 20 numbers ========================================================
          */
        this.button_read20number.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(program1Step==0){
                    // read 20 number from console
                    System.out.println("Start reading numbers...");
                    mcu.setKeyboardBuffer(text_console_keyboard.getText());
                    if (text_console_keyboard.getText()==null || text_console_keyboard.getText().length()==0){
                        JOptionPane.showMessageDialog(null, "type 20 numbers in the console keyboard");
                    }else{
                        printConsole("Below are the 20 numbers: ");
                        mcu.loadProgram(Program1.Pre);
                        mcu.loadProgram(Program1.PG1_20);
                        cpu.setPC(Const.PG1_BASE_1);
                        
                        do{
                            cpu.setMAR(cpu.getPC());
                            cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
                            cpu.setIR(cpu.getIntMBR());
                            runInstruction(cpu.getBinaryStringOfIR(), cpu, mcu);
                        }while(cpu.getPC() <= Const.PG1_END_1 && cpu.getPC() >= Const.PG1_BASE_1);
                        refreshPanel();
                        program1Step = 1;
                        printConsole("Please enter 1 number (end with ',') and press compare button");
                    }
                }    
            }
        });
        
        this.button_compare.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(program1Step==1){
                    // read 1 number 
                    System.out.println("read 1 number...");
                    mcu.loadProgram(Program1.PG1_20);
                    cpu.setPC(Const.PG1_BASE_1);
                    mcu.setKeyboardBuffer(text_console_keyboard.getText());
                    do{
                        cpu.setMAR(cpu.getPC());
                        cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
                        cpu.setIR(cpu.getIntMBR());
                        runInstruction(cpu.getBinaryStringOfIR(), cpu, mcu);
                    }while (cpu.getPC() <= Const.PG1_END_1 && cpu.getPC() >= Const.PG1_BASE_1);
                    
                    System.out.println("start comparing...");
                    printConsole("compare result is (the closeset number is");
                    mcu.loadProgram(Program1.Compare);
                    cpu.setPC(Const.PG1_BASE_2);
                    
                    do{
                        cpu.setMAR(cpu.getPC());
                        cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
                        cpu.setIR(cpu.getIntMBR());
                        runInstruction(cpu.getBinaryStringOfIR(), cpu, mcu);
                        System.out.println(mcu.fetchFromMemory(27)+" ");
                    }while(cpu.getPC() <= Const.PG1_END_2 && cpu.getPC() >= Const.PG1_BASE_2);
                    
                    System.out.println("print the result in address 30");
                    mcu.loadProgram(Program1.PrintResult1);
                    cpu.setPC(Const.PG1_BASE_3);
                    do{
                        cpu.setMAR(cpu.getPC());
                        cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
                        cpu.setIR(cpu.getIntMBR());
                        runInstruction(cpu.getBinaryStringOfIR(), cpu, mcu);
                        System.out.println(mcu.fetchFromMemory(30)+" ");
                    } while(cpu.getPC() <= Const.PG1_END_3 && cpu.getPC() >= Const.PG1_BASE_3);
                    
                    refreshPanel();
                    program1Step = 0;
                        
                    
                }
            }
        });
        
        //Load sentence
        this.button_load.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(program2Step==0){
                    // read 6 sentences from file
                    System.out.println("Start reading sentences...");
                    
                    //TODO: add readfiles here
                    //String sentences = readfiles();
                    String sentences = "Hello every one this is team 3. "
                            +"Our team members are Yi Qian, Kailing Huang, Siyu Sun, Xiaokun Xu. "
                            +"We are from China different provinces.\n "
                            +"Python is our favourite programming language. "
                            +"It has efficient data structure and simple grammer. "
                            +"Python makes program easier. ";
                    mcu.setCardBuffer(sentences);
                    mcu.loadProgram(Program2.PRE);
                    mcu.loadProgram(Program2.PROGRAM2_1);
                    cpu.setPC(Const.PG2_BASE1);
                    
                    do{
                        cpu.setMAR(cpu.getPC());
                        cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
                        cpu.setIR(cpu.getIntMBR());
                        runInstruction(cpu.getBinaryStringOfIR(), cpu, mcu);
                        System.out.println(mcu.fetchFromMemory(20)+" ");
                    } while(cpu.getPC() <= Const.PG2_END1 && cpu.getPC() >= Const.PG2_BASE1);
                    
                    printConsole("Please enter a word need to be searched in console keyboard...");
                    refreshPanel();
                    program2Step = 1;
                    
                }    
            }
        });
        
        //Find word
        this.button_find.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(program2Step==1){
                    System.out.println("Start reading words...");
                    if(text_console_keyboard.getText()==null|| text_console_keyboard.getText().length()==0){
                        JOptionPane.showMessageDialog(null, "type a word in the console keyboard!");
                    }else{
                        //read word
                        printConsole("search reault is ");
                        mcu.loadProgram(Program2.PROGRAM2_2);
                        cpu.setPC(Const.PG2_BASE2);
                        mcu.setKeyboardBuffer(text_console_keyboard.getText());
                        do{
                            cpu.setMAR(cpu.getPC());
                            cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
                            cpu.setIR(cpu.getIntMBR());
                            runInstruction(cpu.getBinaryStringOfIR(), cpu, mcu);
                            System.out.println(mcu.fetchFromMemory(80)+" ");
                            System.out.println(cpu.getRnByNum(1));
                        }while(cpu.getPC()<=Const.PG2_END2 && cpu.getPC() > Const.PG2_BASE2);
                        
                        //search word
                        printConsole("the word number is: ");
                        mcu.loadProgram(Program2.PROGRAM2_3);
                        cpu.setPC(Const.PG2_BASE3);
                        do{
                            cpu.setMAR(cpu.getPC());
                            cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
                            cpu.setIR(cpu.getIntMBR());
                            runInstruction(cpu.getBinaryStringOfIR(), cpu, mcu);
                            System.out.println(cpu.getRnByNum(1));
                            System.out.println(cpu.getRnByNum(2));
                        }while(cpu.getPC()<=Const.PG2_END3 && cpu.getPC() >= Const.PG2_BASE3);
                        
                        //print 1
                        System.out.println("print result in address 28");                        
                        mcu.loadProgram(Program2.PrintResult1);
                        cpu.setPC(Const.PG2_BASE4);
                        do{
                            cpu.setMAR(cpu.getPC());
                            cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
                            cpu.setIR(cpu.getIntMBR());
                            runInstruction(cpu.getBinaryStringOfIR(), cpu, mcu);
                        } while(cpu.getPC() <= Const.PG2_END4 && cpu.getPC() >= Const.PG2_BASE4);
                        
                        //print 2
                        System.out.println("print result in address 29");
                        printConsole("the sentence number is: ");
                        mcu.loadProgram(Program2.PrintResult2);
                        cpu.setPC(Const.PG2_BASE5);
                        do{
                            cpu.setMAR(cpu.getPC());
                            cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
                            cpu.setIR(cpu.getIntMBR());
                            runInstruction(cpu.getBinaryStringOfIR(), cpu, mcu);
                        }while(cpu.getPC() <= Const.PG2_END5 && cpu.getPC() >= Const.PG2_BASE5);
                        refreshPanel();                                
                      
                        program2Step = 0;
                    }
                    
                }    
            }
        });   



        //panel control by single step run halt
        this.panel_control=new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        
        this.label_Address=new JLabel("Address: ");
        this.label_Address.setPreferredSize(new Dimension(120,50));
        this.label_Address.setHorizontalAlignment(JLabel.RIGHT);
        this.panel_control.add(label_Address);
        
        this.text_Address=new JTextField();
        this.text_Address.setPreferredSize(new Dimension(80, 30));
        this.panel_control.add(this.text_Address);
        
        this.label_Value=new JLabel("Value:");
        this.label_Value.setPreferredSize(new Dimension(40,50));
        this.label_Value.setHorizontalAlignment(JLabel.RIGHT);
        this.panel_control.add(label_Value);
                         
        this.text_Val=new JTextField();
        this.text_Val.setPreferredSize(new Dimension(80, 30));
        this.panel_control.add(text_Val);
        
        this.button_memory=new JButton("D/S");
        this.button_memory.setPreferredSize(new Dimension(60, 50));
        this.panel_control.add(button_memory);
        
        this.button_singlestep=new JButton("Single");
        this.button_singlestep.setPreferredSize(new Dimension(90, 50));
        this.panel_control.add(this.button_singlestep);
        
        this.button_run=new JButton("Run");        
        this.button_run.setPreferredSize(new Dimension(90, 50));
        this.panel_control.add(this.button_run);
        
        this.button_halt=new JButton("Halt");
        this.button_halt.setPreferredSize(new Dimension(90, 50));
        this.panel_control.add(this.button_halt);
               
        this.button_IPL=new JButton("IPL");
        this.button_IPL.setPreferredSize(new Dimension(120, 50));
        this.panel_control.add(this.button_IPL);
       
        this.panel_leftbot.add(this.panel_control);
        
        
        this.button_memory.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mem");
                getKey = text_Address.getText();
                getValue = text_Val.getText();
                System.out.println(getValue);
                if(getValue.equals("")){
                    SearchINAddress(getKey);
                }
                else{
                    DepositINAddress(getKey, getValue);
                }
            }
        });
        

        
        this.button_singlestep.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sin");
                Single();
            }
        });
        
        this.button_run.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Run");
                Run();
            }
        });
        
        this.button_halt.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Halt");
                Halt();
            }
        });

        this.button_IPL.addActionListener(new java.awt.event.ActionListener() {       	
            //enable all button      	
            @Override
            public void actionPerformed(ActionEvent e) {
                initialCPU();
                cpu.setPC(Const.PC_BASE);
                //mcu.loadProgram(Const.BASE_PROGRAM);
                program1Step = 0;
                enableButton();
                initialReg();
                printConsole("IPL finish!");
                
            }
        });


        //console text area  right part
        this.panel_console_print=new JPanel(new BorderLayout(0,0));
        this.text_console_print=new JTextArea();
        this.scrollPane_console_printer = new JScrollPane();
        //this.text_console_print.setPreferredSize(new Dimension(400, 300));
        this.scrollPane_console_printer.setPreferredSize(new Dimension(400,300));
        this.text_console_print.setLineWrap(true);
        this.label_console_printer=new JLabel("Console Printer");
        this.panel_console_print.add(label_console_printer,BorderLayout.NORTH);
        this.scrollPane_console_printer.setViewportView(text_console_print);
        this.panel_console_print.add(scrollPane_console_printer,BorderLayout.CENTER);
       
        this.panel_console_keyboard=new JPanel(new BorderLayout(10,10));
        this.text_console_keyboard=new JTextArea();
        this.text_console_keyboard.setPreferredSize(new Dimension(400, 270));
        this.text_console_keyboard.setLineWrap(true);
        this.label_console_keyboard=new JLabel("Console Keyboard");
        
        this.panel_console_keyboard.add(label_console_keyboard,BorderLayout.NORTH);
        this.panel_console_keyboard.add(text_console_keyboard,BorderLayout.CENTER);
        
        
        this.panel_console_cache=new JPanel(new BorderLayout(10,10));
        this.text_console_cache=new JTable(16,2);
        this.text_console_cache.setEnabled(false);
        this.text_console_cache.setShowGrid(false);
        
        this.scrollPane_cache = new JScrollPane();
        this.scrollPane_cache.setViewportView(text_console_cache);
        
        this.text_console_cache.setModel(new DefaultTableModel(
                new Object[][] { { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
                        { null, null },  { null, null }, { null, null },{ null, null }, { null, null }, { null, null },{ null, null },
                        { null, null }, { null, null },{ null, null },{ null, null }, { null, null },  },
                new String[] { "Tag", "Data" }));
        
        this.scrollPane_cache.setPreferredSize(new Dimension(400, 210));
        
        this.label_console_cache=new JLabel("Cache");
        this.panel_console_cache.add(label_console_cache,BorderLayout.NORTH);
        this.panel_console_cache.add(this.scrollPane_cache,BorderLayout.CENTER);
        
        //console button
        this.button_console=new JButton("CLEAR");
        this.button_console.setPreferredSize(new Dimension(100, 35));
        
        this.button_enter=new JButton("ENTER");
        this.button_enter.setPreferredSize(new Dimension(100, 35));
        
        
        
        
        this.panel_clearbutton=new JPanel(new FlowLayout(FlowLayout.RIGHT,75,0));               
        this.panel_clearbutton.add(this.button_console);  
        this.panel_clearbutton.add(this.button_enter); 
        
        //disable all button
       this.button_compare.setEnabled(false);
       this.button_read20number.setEnabled(false);
       this.button_load.setEnabled(false);
       this.button_find.setEnabled(false);
       this.button_enter.setEnabled(false);
       this.button_run.setEnabled(false);
       this.button_halt.setEnabled(false);
       this.button_deposit.setEnabled(false);
       this.button_singlestep.setEnabled(false);
       this. button_console.setEnabled(false);
       this. button_memory.setEnabled(false);
       this.FloatAdd_Button.setEnabled(false);
       this.FloatConver_Button.setEnabled(false);
       this.VectorAdd_Button.setEnabled(false);
       this.VectorSub_Button.setEnabled(false);
      //
        
        
        // console text panel combine
        this.panel_consoletext=new JPanel(new BorderLayout(10,10));
        this.panel_consoletext.add(this.panel_console_print,BorderLayout.NORTH);   
        this.panel_consoletext.add(this.panel_console_keyboard,BorderLayout.CENTER);  
        this.panel_consoletext.add(this.panel_console_cache,BorderLayout.SOUTH);  
               
            
        
        this.button_console.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clear");
                ConsoleString = "";
                text_console_print.setText("");
                text_console_keyboard.setText("");
            }
        });      
        
       

        //panel right layout setting
        this.panel_console=new JPanel(new BorderLayout(10,10));
        this.panel_console.add(this.panel_consoletext,BorderLayout.NORTH);            
        this.panel_console.add(this.panel_clearbutton,BorderLayout.CENTER);
        //this.panel_console.add(this.panel_lastline,BorderLayout.SOUTH);
        
        
       
        //panel_left layout setting
        this.panel_register.setPreferredSize(new Dimension(900, 700));
        this.panel_left.setPreferredSize(new Dimension(900, 950));
        this.panel_left.add(panel_register,BorderLayout.NORTH);
        this.panel_left.add(panel_leftbot, BorderLayout.CENTER);
        
        //create borderline
        this.panel_register.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Rigester"));
        this.panel_leftbot.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Control"));
        this.panel_console.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"User console"));
       
        
        
        //Frame add
        this.Global = new JPanel(new BorderLayout(10,10));
        this.Global.add(this.panel_console, BorderLayout.EAST);
        this.Global.add(this.panel_left, BorderLayout.CENTER);
        this.frame.add(Global);
        this.frame.setVisible(true);
        this.frame.pack();
        
        
    } 
    
    public ControlPanel()
    {
        initComponents();
    }
   
       
    private void enableButton(){
        this.button_compare.setEnabled(true);
        this.button_read20number.setEnabled(true);
        this.button_load.setEnabled(true);
        this.button_find.setEnabled(true);
        this.button_enter.setEnabled(true);
        this.button_run.setEnabled(true);
        this.button_halt.setEnabled(true);
        this.button_deposit.setEnabled(true);
        this.button_singlestep.setEnabled(true);
        this. button_console.setEnabled(true);
        this. button_memory.setEnabled(true);
        this.FloatAdd_Button.setEnabled(true);
        this.FloatConver_Button.setEnabled(true);
        this.VectorAdd_Button.setEnabled(true);
        this.VectorSub_Button.setEnabled(true);
    }
    
    /* Launch the application */
    public static void main(String[] args) {      
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                new ControlPanel();
            }
        });
    }
    
    public void Halt(){
        this.tempPC = cpu.getPC();
        this.tempIR = cpu.getBinaryStringOfIR();
        printConsole("Halt - PC: " + this.tempPC +", instruction: "+ tempIR);
        
        cpu.setMAR(cpu.getPC());
        cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
        cpu.setIR(cpu.getIntMBR());
        String ins = cpu.getBinaryStringOfIR();
        printConsole("PC: " + cpu.getPC() + ", instruction: " + ins);
        runInstruction(ins, cpu, mcu);
    }
      
    public void Run(){
        cpu.setMAR(this.tempPC);
        cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
        cpu.setIR(cpu.getIntMBR());
        String ins = cpu.getBinaryStringOfIR();
        printConsole("PC: " + cpu.getPC() + ", instruction: " + ins);
        runInstruction(ins, cpu, mcu);
        Run();
    }
    
    public void refreshPanel(){
        for (Component com : Global.getComponents()) {
            if (com instanceof JPanel) {
                JPanel panel = (JPanel) com;
                int regVal = 0;
                int bitLong = 0;
                String bitString = "";
                int i = 0;
                for (Component comm : panel.getComponents()) {
                    if (comm instanceof JLabel) {
                        JLabel lbl = (JLabel) comm;
                        regVal = cpu.getRegByName(lbl.getText());
                        bitLong = cpu.getBitLengthByName(lbl.getText());
                        bitString = StringUtil.decimalToBinary(regVal, bitLong);
                        i = bitLong;
                    }
                    /*
                    if (comm instanceof JRadioButton) {
                        JRadioButton rdb = (JRadioButton) comm;
                        if (bitString.charAt(bitLong - i) == '1') {
                            rdb.setSelected(true);
                        } else {
                            rdb.setSelected(false);
                        }
                        i--;
                    }
                    */
                    if (comm instanceof JTextField) {
                        JTextField txt = (JTextField) comm;
                        txt.setText(String.valueOf(regVal));
                    }
                }
            }
        }        
    }
    
    public void Single(){
        cpu.setMAR(cpu.getPC());
        cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
        cpu.setIR(cpu.getIntMBR());
        String ins = cpu.getBinaryStringOfIR();
        printConsole("PC: " + cpu.getPC() + ", instruction: " + ins);
        runInstruction(ins, cpu, mcu);
    }
    
    private void SearchINAddress(String keyIN){
        int address = Integer.parseInt(keyIN);
        if (address > mcu.getCurrentMemorySize() - 1 || address < 0) {
            JOptionPane.showMessageDialog(null,
            "Memory between 0 and " + (mcu.getCurrentMemorySize() - 1) + "!");
            } else {
                int value = mcu.fetchFromMemory(address);
                text_Val.setText(String.valueOf(value));
            }
    }
    
    private void DepositINAddress(String keyIN, String AddIN){       
        
        int address = Integer.parseInt(keyIN);
        int value = Integer.parseInt(AddIN);
        if (address > mcu.getCurrentMemorySize() - 1 || address < 0) {
            JOptionPane.showMessageDialog(null,
                "Memory between 0 and " + (mcu.getCurrentMemorySize() - 1) + "!");
        } else if (value > 0xffff || value < 0) {
                JOptionPane.showMessageDialog(null, "Value between 0 and 65535!");
        } else {
            mcu.storeInMemory(address, value);
            text_Address.setText("0");
            text_Val.setText("");
        printConsole("store memory address " + address + " with value " + value);
        }
    }
    
    private void ShowNumberO(String TextPC, String TextMAR, String TextMBR, String TextIR, int CPU_PC, int CPU_MAR, String CPU_MBR, int CPU_IR, boolean showO){
        int NumPC, NumMAR, NumMBR, NumIR;
        //show PC
        if(!(TextPC.equals(""))){
            NumPC = Integer.parseInt(TextPC);
            if(NumPC>0 && NumPC<4096){
                NumPC = NumPC;
                cpu.setPC(NumPC);                
                ConsoleString = ConsoleString + "\r\nchange PC to "+TextPC;
                printConsole(ConsoleString);
            }
            else{
                NumPC = CPU_PC;
            }
        }
        else{
            NumPC = CPU_PC;
        }
        System.out.println("B");
        for(int i=11; i>=0; i--){
            if(NumPC%2==1){
                if(showO){this.Pc[i].setBackground(Color.blue);}
                else{this.Pc[i].setBackground(Color.red);}
            }
            else{
                this.Pc[i].setBackground(Color.white);
            }
            NumPC/=2;
        }
        
        //show MAR
        if(!(TextMAR.equals(""))){
            NumMAR = Integer.parseInt(TextMAR);
            if(NumMAR>0 && NumMAR<65536){
                NumMAR = NumMAR;
                cpu.setMAR(NumMAR);
                ConsoleString = ConsoleString + "\r\nchange MAR to "+TextMAR;
                printConsole(ConsoleString);
            }
            else{
                NumMAR = CPU_MAR;
            }
        }
        else{
            NumMAR = CPU_MAR;
        }
        for(int i=15; i>=0; i--){
            if(NumMAR%2==1){
                if(showO){this.Mar[i].setBackground(Color.blue);}
                else{this.Mar[i].setBackground(Color.red);}
            }
            else{
                this.Mar[i].setBackground(Color.white);
            }
            NumMAR/=2;
        }
            
        //show MBR
        if(!(TextMBR.equals(""))){
            NumMBR = Integer.parseInt(TextMBR);
            if(NumMBR>0 && NumMBR<65536){
                NumMBR = NumMBR;
                cpu.setMBR(TextMBR);
                ConsoleString = ConsoleString + "\r\nchange MBR to "+TextMBR;
                printConsole(ConsoleString);
            }
            else{
                NumMBR = Integer.parseInt(CPU_MBR);
            }
        }
        else{
            NumMBR = Integer.parseInt(CPU_MBR);
        }
        for(int i=15; i>=0; i--){
            if(NumMBR%2==1){
                if(showO){this.Mbr[i].setBackground(Color.blue);}
                else{this.Mbr[i].setBackground(Color.red);}
            }
            else{
                this.Mbr[i].setBackground(Color.white);
            }
            NumMBR/=2;
        }
        
        //show IR
        if(!(TextIR.equals(""))){
            NumIR = Integer.parseInt(TextIR);
            if(NumIR>0 && NumIR<65536){
                NumIR = NumIR;
                cpu.setIR(Integer.parseInt(TextIR));
                ConsoleString = ConsoleString + "\r\nchange IR to "+TextIR;
                printConsole(ConsoleString);
            }
            else{
                NumIR = CPU_IR;
            }
        }
        else{
            NumIR = CPU_IR;
        }
        for(int i=15; i>=0; i--){
            if(NumIR%2==1){
                if(showO){this.Ir[i].setBackground(Color.blue);}
                else{this.Ir[i].setBackground(Color.red);}
            }
            else{
                this.Ir[i].setBackground(Color.white);
            }
            NumIR/=2;
        }        

    }
    
    private void ShowNumberR(String TextR0, String TextR1, String TextR2, String TextR3, int CPU_R0, int CPU_R1, int CPU_R2, int CPU_R3, boolean showR){
        int NumR0, NumR1, NumR2, NumR3;
        //show R0
        if(!(TextR0.equals(""))){
            NumR0 = Integer.parseInt(TextR0);
            if(NumR0>0 && NumR0<65536){
                NumR0 = NumR0;
                cpu.setRnByNum(0, Integer.parseInt(TextR0));
                ConsoleString = ConsoleString + "\r\nchange R0 to "+TextR0;
                printConsole(ConsoleString);
            }
            else{
                NumR0 = CPU_R0;
            }
        }
        else{
            NumR0 = CPU_R0;
        }
        for(int i=15; i>=0; i--){
            if(NumR0%2==1){
                if(showR){this.Register0[i].setBackground(Color.blue);}
                else{this.Register0[i].setBackground(Color.red);}
            }
            else{
                this.Register0[i].setBackground(Color.white);
            }
            NumR0/=2;
        }
        
        //show R1
        if(!(TextR1.equals(""))){
            NumR1 = Integer.parseInt(TextR1);
            if(NumR1>0 && NumR1<65536){
                NumR1 = NumR1;
                cpu.setRnByNum(1, Integer.parseInt(TextR1));
                ConsoleString = ConsoleString + "\r\nchange R1 to "+TextR1;
                printConsole(ConsoleString);
            }
            else{
                NumR1 = CPU_R1;
            }
        }
        else{
            NumR1 = CPU_R1;
        }
        for(int i=15; i>=0; i--){
            if(NumR1%2==1){
                if(showR){this.Register1[i].setBackground(Color.blue);}
                else{this.Register1[i].setBackground(Color.red);}
            }
            else{
                this.Register1[i].setBackground(Color.white);
            }
            NumR1/=2;
        }
        
        //show R2
        if(!(TextR2.equals(""))){
            NumR2 = Integer.parseInt(TextR2);
            if(NumR2>0 && NumR2<65536){
                NumR2 = NumR2;
                cpu.setRnByNum(1, Integer.parseInt(TextR2));
                ConsoleString = ConsoleString + "\r\nchange R2 to "+TextR2;
                printConsole(ConsoleString);
            }
            else{
                NumR2 = CPU_R2;
            }
        }
        else{
            NumR2 = CPU_R2;
        }
        for(int i=15; i>=0; i--){
            if(NumR2%2==1){
                if(showR){this.Register2[i].setBackground(Color.blue);}
                else{this.Register2[i].setBackground(Color.red);}
            }
            else{
                this.Register2[i].setBackground(Color.white);
            }
            NumR2/=2;
        }
        
        //show R3
        if(!(TextR3.equals(""))){
            NumR3 = Integer.parseInt(TextR3);
            if(NumR3>0 && NumR3<65536){
                NumR3 = NumR3;
                cpu.setRnByNum(3, Integer.parseInt(TextR3));
                ConsoleString = ConsoleString + "\r\nchange R3 to "+TextR3;
                printConsole(ConsoleString);
            }
            else{
                NumR3 = CPU_R1;
            }
        }
        else{
            NumR3 = CPU_R3;
        }
        for(int i=15; i>=0; i--){
            if(NumR3%2==1){
                if(showR){this.Register3[i].setBackground(Color.blue);}
                else{this.Register3[i].setBackground(Color.red);}
            }
            else{
                this.Register3[i].setBackground(Color.white);
            }
            NumR3/=2;
        }
        
    }
    
    private void ShowNumberX(String TextX1, String TextX2, String TextX3, int CPU_X1, int CPU_X2, int CPU_X3, boolean showX){
        int NumX1, NumX2, NumX3;
        //show X1
        if(!(TextX1.equals(""))){
            NumX1 = Integer.parseInt(TextX1);
            if(NumX1>0 && NumX1<65536){
                NumX1 = NumX1;
                cpu.setXnByNum(0, NumX1);
                ConsoleString = ConsoleString + "\r\nchange X1 to "+TextX1;
                printConsole(ConsoleString);
            }
            else{
                NumX1 = CPU_X1;
            }
        }
        else{
            NumX1 = CPU_X1;
        }
        for(int i=15; i>=0; i--){
            if(NumX1%2==1){
                if(showX){this.Index_Reg1[i].setBackground(Color.blue);}
                else{this.Index_Reg1[i].setBackground(Color.red);}
            }
            else{
                this.Index_Reg1[i].setBackground(Color.white);
            }
            NumX1/=2;
        }
        
        //show X2
        if(!(TextX2.equals(""))){
            NumX2 = Integer.parseInt(TextX2);
            if(NumX2>0 && NumX2<65536){
                NumX2 = NumX2;
                cpu.setXnByNum(1, NumX2);
                ConsoleString = ConsoleString + "\r\nchange X2 to "+TextX2;
                printConsole(ConsoleString);
            }
            else{
                NumX2 = CPU_X2;
            }
        }
        else{
            NumX2 = CPU_X2;
        }
        for(int i=15; i>=0; i--){
            if(NumX2%2==1){
                if(showX){this.Index_Reg2[i].setBackground(Color.blue);}
                else{this.Index_Reg2[i].setBackground(Color.red);}
            }
            else{
                this.Index_Reg2[i].setBackground(Color.white);
            }
            NumX2/=2;
        }

        //show X3
        if(!(TextX3.equals(""))){
            NumX3 = Integer.parseInt(TextX3);
            if(NumX3>0 && NumX3<65536){
                NumX3 = NumX3;
                cpu.setXnByNum(2, NumX3);
                ConsoleString = ConsoleString + "\r\nchange X3 to "+TextX3;
                printConsole(ConsoleString);
            }
            else{
                NumX3 = CPU_X3;
            }
        }
        else{
            NumX3 = CPU_X3;
        }
        for(int i=15; i>=0; i--){
            if(NumX3%2==1){
                if(showX){this.Index_Reg3[i].setBackground(Color.blue);}
                else{this.Index_Reg3[i].setBackground(Color.red);}
            }
            else{
                this.Index_Reg3[i].setBackground(Color.white);
            }
            NumX3/=2;
        }
        
    }
    
    private void ShowNumberZ(int CPU_MFR, int CPU_CC){
        int NumMFR, NumCC;
        //show MFR
        NumMFR = CPU_MFR;
        for(int i=3; i>=0; i--){
            if(NumMFR%2==1){
                this.Mfr[i].setBackground(Color.blue);
            }
            else{
                this.Mfr[i].setBackground(Color.white);                
            }
            NumMFR/=2;
        }
        
        //show CC
        NumCC = CPU_CC;;
        for(int i=3; i>=0; i--){
            if(NumCC%2==1){
                this.Cc[i].setBackground(Color.blue);
            }
            else{
                this.Cc[i].setBackground(Color.white);                
            }
            NumCC/=2;
        }
    }
    
    public void initialCPU(){
        this.cpu = new CPU();
        this.mcu = new MCU();
    }
    
    private void initialReg(){
        //put the instructions and required data into main memory
        cpu.setXnByNum(2, 1000);//initialize index register
        cpu.setXnByNum(1, 700);
        cpu.setXnByNum(0, 500);
        cpu.setRnByNum(0, 0);
        cpu.setRnByNum(1, 0);
        cpu.setRnByNum(2, 0);
        cpu.setRnByNum(3, 0);
        cpu.setMBR("0");
        cpu.setIR(0);

        ConsoleString = ConsoleString + "\r\nstart working, load instruction succeed!";
        this.printConsole(ConsoleString);
    }
    
    private void runInstruction(String instruction, CPU cpu, MCU mcu) {

        // execute button event
        String opCode = instruction.substring(0, 6);
        try {
            if (Const.OPCODE.containsKey(opCode)) {

                Abstractinstruction instr = (Abstractinstruction) Class
                        .forName("instruction." + Const.OPCODE.get(opCode)).newInstance();
                instr.execute(instruction, cpu, mcu);
                System.out.println("PC: " + cpu.getPC() + ", instruction: " + instruction);
                // printConsole("instruction: " + instruction);
                refreshCacheTable();
                refreshConsoleBuffer();

                String message = instr.getExecuteMessage();
                System.out.println(message);
                //
                // TODO do something with this message
                //

            } else {
                // we don't have that kind of instruction
                throw new MachineFaultException(Const.FaultCode.ILL_OPRC.getValue(),
                        Const.FaultCode.ILL_OPRC.getMessage());
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MachineFaultException t) {
            // handle the machine fault

            t.printStackTrace();
            //handleMachineFault(t.getFaultCode(), t.getMessage());
        }
    }
        
    private void refreshConsoleBuffer() {
        if (mcu.getPrinterBuffer() != null) {
            text_console_print.append(mcu.getPrinterBuffer());
            mcu.setPrinterBuffer("");
        }
        if (mcu.getKeyboardBuffer() != null) {
            text_console_keyboard.setText(mcu.getKeyboardBuffer());
        }
    }
    
    private void refreshCacheTable() {
        int row = 0;
        for (Cache.CacheLine line : mcu.getCache().getCacheLines()) {
            this.text_console_cache.setValueAt(line.getTag(), row, 0);
            this.text_console_cache.setValueAt(line.getData(), row, 1);
            row++;
        }

    }
    
    public void printConsole(String information){
        this.text_console_print.append(information + "\n");
        
    }
    
    private int FloatConvert(float num){
        String sign = "0";
        String exp = "0000000";
        String Man = "00000000";
        
        int Intpart = (int)num;
        float Floatpart = num-Intpart;
        
        String Intpart_string = trans(Intpart);
        String Floatpart_strinf = trans(Floatpart);
        
        if(num<0){
            sign = "1";
        }
        
        return 0;
        
    }
    
    private String trans(int num){
        String temp = "";
        while(num!=0){
            temp = (num%2)+temp;
            num = num/2;
        }
        return temp;
    }
    
    private String trans(float num){
        if(num>1){
            return "false";
        }
        String temp = "";
        int bit_num = 10;
        for(int i=0; i<bit_num;i++){
            num *=2;
            if(num>=1){
                temp += "1";
                num = num-1;
            }else{
                temp+="0";
            }
        }
        return temp;
    }
    
}
