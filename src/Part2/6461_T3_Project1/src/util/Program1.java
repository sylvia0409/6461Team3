/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashMap;

/**
 *
 * @author yiqian
 */
public class Program1 {
    
    // data need to preload in memory
    public static final HashMap<String, Integer> Pre = new HashMap<>();
    static{
        Pre.put("6", 44);   // 44 ascii of ','
        Pre.put("28", 302); // read 20 number loop start address
        Pre.put("29", 7);   // the lowest address of 20 numbers
        Pre.put("30", 20);  // the number of number need to read in Program 1
        Pre.put("31", 505); // address need in compare program
    }
    
    // Read 20 numbers
    public static final HashMap<String, Integer> PG1_20 = new HashMap<>();
    static{
        /**
         * R3 index, count how many number input
         * R1 input/ address save number
         * R2 address to jump
         * R0 use to set value
         */
        PG1_20.put("300", 0x7c8f);      //SRC set R0=0
        PG1_20.put("301", 0xf3e);       //LDA R3<-c(30)
        
        // read loop entrence
        // input ',' ?
        PG1_20.put("302", 0xe26);       //LDA R2<-c(6)
        PG1_20.put("303", 0xf500);      //IN  R1<-input
        PG1_20.put("304", 0x5980);      //TRR R3==R0 ? cc(3)=1:cc(3)=0
        PG1_20.put("305", 0xe3c);       //LDA R2<-c(28)
        PG1_20.put("306", 0x1a14);      //AIR R2<-R2+imm(20)
        PG1_20.put("307", 0xa1c);       //STR c(28)<-R2
        PG1_20.put("308", 0x333c);      //JCC cc(3)==1 ? PC<-c(28):PC<-PC+1
        
        // input is number
        PG1_20.put("309", 0xe3c);       //LDA R2<-c(28)
        PG1_20.put("310", 0x1a14);      //AIR R2<-R2+imm(20)
        PG1_20.put("311", 0xa1c);       //STR c(28)<-R2
        PG1_20.put("312", 0x343c);      //JMA PC<-c(28)
        
        PG1_20.put("322", 0x83d);       //STR c(29)<-R0
        PG1_20.put("323", 0x7c8f);      //SRC R0<-0
        PG1_20.put("324", 0x180a);      //AIR R0<-R0+imm(10)
        PG1_20.put("325", 0xf801);      //OUT output<-R0
        PG1_20.put("326", 0xd3d);       //LDA R1<-c(29)
        PG1_20.put("327", 0x1901);      //AIR R1<-R1+imm(1)
        PG1_20.put("328", 0x91d);       //STR c(29)<-R1
        PG1_20.put("329", 0x7c8f);      //SRC R0<-0
        PG1_20.put("330", 0xe3c);       //LDA R2<-c(28)
        PG1_20.put("331", 0x1a14);      //AIR R2<-R2+imm(20)
        PG1_20.put("332", 0x1a14);      //AIR R2<-R2+imm(20)
        PG1_20.put("333", 0xa1c);       //STR c(28)<-R2
        PG1_20.put("334", 0x343c);      //JMA PC<-c(28)
        
        
        PG1_20.put("342", 0x83D);       //STR c(29)<-R0
        PG1_20.put("343", 0x103D);      //AMR R0<-R0+c(29)
        PG1_20.put("344", 0x103D);      //AMR R0<-R0+c(29)
        PG1_20.put("345", 0x103D);      //AMR R0<-R0+c(29)
        PG1_20.put("346", 0x103D);      //AMR R0<-R0+c(29)
        PG1_20.put("347", 0x103D);      //AMR R0<-R0+c(29)
        PG1_20.put("348", 0x103D);      //AMR R0<-R0+c(29)
        PG1_20.put("349", 0x103D);      //AMR R0<-R0+c(29)
        PG1_20.put("350", 0x103D);      //AMR R0<-R0+c(29)
        PG1_20.put("351", 0x103D);      //AMR R0<-R0+c(29)
        PG1_20.put("352", 0xf901);      //OUT output<-R1
        PG1_20.put("353", 0x1d18);      //SIR R1<-R1-imm(24)
        PG1_20.put("354", 0x1d18);      //SIR R1<-R1-imm(24)
        PG1_20.put("355", 0x93d);       //STR c(29)<-R1
        PG1_20.put("356", 0x103d);      //AMR R0<-R0+c(29)
        PG1_20.put("357", 0xe3c);       //LDA R2<-c(28)
        PG1_20.put("358", 0x1e14);      //SIR R2-imm(20)
        PG1_20.put("359", 0x1e14);      //SIR R2-imm(20)
        PG1_20.put("360", 0xa1c);       //STR c(28)<-R2
        PG1_20.put("361", 0x343c);      //JMA PC<-c(28)
        PG1_20.put("362", 0xe3c);       //LDA R2<-c(28)
        PG1_20.put("363", 0x1e14);      //SIR R2<-R2-imm(20)
        PG1_20.put("364", 0x1e14);      //SIR R2<-R2-imm(20)
        PG1_20.put("365", 0x1e14);      //SIR R2<-R2-imm(20)
        PG1_20.put("366", 0xa1c);       //STR c(28)<-R2
        PG1_20.put("367", 0x433c);      //SOB R3-1>0?PC<-c(28):PC++
        PG1_20.put("368", 0x7c8f);      //SRC R0<-0
        PG1_20.put("369", 0x1801);      //AIR R0<-R0+imm(1)
        PG1_20.put("370", 0x81e);       //STR c(30)<-R0 
        
    }
    
    public static final HashMap<String, Integer> Compare = new HashMap<>();
    static{
        /**
         * address 7-26 save 20 numbers
         * addfress 27 save the number need to compared
         * address 28, 29s save distance between Xi and input distance, Xj and input distance
         */
        //Load 1st number
        Compare.put("500", 0x407);      //LDR R0<-c(7)
        Compare.put("501", 0x141B);     //SMR R0<-R0-c(27)
	Compare.put("502", 0x443F);     //JGE R0>0?PC<-c(31):PC++  
        //X1<input
	Compare.put("503", 0x41B);      //LDR R0<-c(27)
        Compare.put("504", 0x1407);     //SMR R0<-R0-c(7)    R0=input-X1
        //X1>=input
	Compare.put("505", 0x81C);      //STR c(28)<-R0      put X1-input at final of 20
        Compare.put("506", 0x71F);      //LDR R3<-c(31)
	Compare.put("507", 0x1B0B);     //AIR R3<-R3+imm(11)
	Compare.put("508", 0xB1F);      //STR c(31)<-R3       c(31)=516
        // X1>=input?c(28)=X1:input-X1
         
        
        //Load 2nd number
        Compare.put("509", 0x408);      //LDR R0<-c(8)
	Compare.put("510", 0x141B);     //SMR R0<-R0-c(27)
	Compare.put("511", 0x81D);      //STR c(29)<-R0     c(29)=X2-input
	Compare.put("512", 0x443F);     //JGE R0>0?PC<-c(31):PC++       
        //X2<input
	Compare.put("513", 0x41B);      //LDR R0<-c(27)
	Compare.put("514", 0x1408);     //SMR R0 = R0-c(8)      R0=input-X2
	Compare.put("515", 0x81D);      //STR c(29)<-R0         c(29)=input-X2
        //X2>=input
	Compare.put("516", 0x71F);      //LDR R3<-c(31)
	Compare.put("517", 0x1B0C);     //AIR R3<-R3+imm(12)
	Compare.put("518", 0xB1F);      //STR c(31)<-R3        c(31)=528
        // X2>=input?c(29)=X2:input-X2
        
        //Compare X1 distance and X2 distance
	Compare.put("519", 0x41D);      //LDR R0 = c(29)
	Compare.put("520", 0x141C);     //SMR R0 = R0-c(28)
	Compare.put("521", 0x507);      //LDR R1<-c(7)
	Compare.put("522", 0x91E);      //STR c(30)<-R2
	Compare.put("523", 0x61C);      //LDR R2<-c(28)
	Compare.put("524", 0x443F);     //JGE if R0>0 PC=31, else PC++  jump to 528
        //X2 distance to input smaller than X1's
	Compare.put("525", 0x408);      //LDR R0<-c(8)
	Compare.put("526", 0x81E);      //STR c(30)<-R0   c(30)=X2
	Compare.put("527", 0x61D);      //LDR R2<-c(29)
        //X2 distance to input bigger than X1's
	Compare.put("528", 0xA1C);      //STR c(28)<-R2
	Compare.put("529", 0x71F);      //LDR R3<-c(31)                                               
	Compare.put("530", 0x1B09);     //AIR R3<-R3+imm(9)
	Compare.put("531", 0xB1F);      //STR c(31)<-R3     c(31)=537
        
        
        //Load 3rd number
	Compare.put("532", 0x409);      //LDR R0<-c(9)
	Compare.put("533", 0x141B);     //SMR R0 = R0-c(27)
	Compare.put("534", 0x443F);     //JGE if R0>0 PC=31, else PC++
        //X3<input
	Compare.put("535", 0x41B);      //LDR R0<-c(27)
	Compare.put("536", 0x1409);     //SMR R0 = R0-c(9)
        //X3>=input
	Compare.put("537", 0x81D);      //STR c(29)<-R0
	Compare.put("538", 0x71F);      //LDR R3<-c(31)
	Compare.put("539", 0x1B0B);     //AIR R3<-R3+imm(11)
	Compare.put("540", 0xB1F);      //STR c(31)<-R3     c(31)=548
        
        
        //Compare distence in 28 and 29
	Compare.put("541", 0x41D);      //LDR R0 = c(29)
	Compare.put("542", 0x141C);     //SMR R0 = R0-c(28)
	Compare.put("543", 0x443F);     //JGE if R0>0 PC=31, else PC++ jump to 548
        //X3 is closer than X12
	Compare.put("544", 0x61D);      //LDR R2<-c(29)      
	Compare.put("545", 0xA1C);      //STR c(28)<-R2
	Compare.put("546", 0x509);      //LDR R1<-c(9)
	Compare.put("547", 0x91E);      //STR c(30)<-R2
        //X12 is closer than X3
	Compare.put("548", 0x71F);      //LDR R3<-c(31) 
	Compare.put("549", 0x1B08);     //AIR R3<-R3+imm(8)
	Compare.put("550", 0xB1F);      //STR c(31)<-R3     c(31)=556
        
        
        //Load 4th number
	Compare.put("551", 0x40A);      //LDR R0<-c(9)
	Compare.put("552", 0x141B);     //SMR R0 = R0-c(27)
	Compare.put("553", 0x443F);     //JGE if R0>0 PC=31, else PC++ jump to 556
        //X4<input
	Compare.put("554", 0x41B);      //LDR R0<-c(27)
	Compare.put("555", 0x140A);     //SMR R0 = R0-c(10)
        //X4>=input
	Compare.put("556", 0x81D);      //STR c(29)<-R0
	Compare.put("557", 0x71F);      //LDR R3<-c(31)
	Compare.put("558", 0x1B0B);     //AIR R3<-R3+imm(11)
	Compare.put("559", 0xB1F);      //STR c(31)<-R3     c(31)=567
        
        //Compare distance
	Compare.put("560", 0x41D);
	Compare.put("561", 0x141C);
	Compare.put("562", 0x443F);
        
	Compare.put("563", 0x61D);
	Compare.put("564", 0xA1C);
	Compare.put("565", 0x50A);
	Compare.put("566", 0x91E);
        
	Compare.put("567", 0x71F);
	Compare.put("568", 0x1B08);
	Compare.put("569", 0xB1F);      //c(31)=575
        
        
        
        //Load 5th number
	Compare.put("570", 0x40B);
	Compare.put("571", 0x141B);
	Compare.put("572", 0x443F);
        
	Compare.put("573", 0x41B);
	Compare.put("574", 0x140B);
        
	Compare.put("575", 0x81D);
	Compare.put("576", 0x71F);
	Compare.put("577", 0x1B0B);     //c(31)=586
	Compare.put("578", 0xB1F);
        
        //Compare distance
	Compare.put("579", 0x41D);
	Compare.put("580", 0x141C);
	Compare.put("581", 0x443F);
        
	Compare.put("582", 0x61D);
	Compare.put("583", 0xA1C);
	Compare.put("584", 0x50B);
	Compare.put("585", 0x91E);

	Compare.put("586", 0x71F);
	Compare.put("587", 0x1B08);
	Compare.put("588", 0xB1F);      //c(31)=594
        
        
        
        //Load 6th number
	Compare.put("589", 0x40C);
	Compare.put("590", 0x141B);
	Compare.put("591", 0x443F);
        
	Compare.put("592", 0x41B);
	Compare.put("593", 0x140C);
        
	Compare.put("594", 0x81D);
	Compare.put("595", 0x71F);
	Compare.put("596", 0x1B0B);     //c(31)=605
	Compare.put("597", 0xB1F);
        
        //Compare distance
	Compare.put("598", 0x41D);
	Compare.put("599", 0x141C);
	Compare.put("600", 0x443F);
        
	Compare.put("601", 0x61D);
	Compare.put("602", 0xA1C);
	Compare.put("603", 0x50C);
	Compare.put("604", 0x91E);
        
	Compare.put("605", 0x71F);
	Compare.put("606", 0x1B08);
	Compare.put("607", 0xB1F);      //c(31)=613
        
        
        //Load 7th number
	Compare.put("608", 0x40D);
	Compare.put("609", 0x141B);
	Compare.put("610", 0x443F);
        
	Compare.put("611", 0x41B);
	Compare.put("612", 0x140D);
        
	Compare.put("613", 0x81D);
	Compare.put("614", 0x71F);
	Compare.put("615", 0x1B0B);     //c(31)=624
	Compare.put("616", 0xB1F);
        
        //Compare distance
	Compare.put("617", 0x41D);
	Compare.put("618", 0x141C);
	Compare.put("619", 0x443F);
        
	Compare.put("620", 0x61D);
	Compare.put("621", 0xA1C);
	Compare.put("622", 0x50D);
	Compare.put("623", 0x91E);

	Compare.put("624", 0x71F);
	Compare.put("625", 0x1B08);
	Compare.put("626", 0xB1F);      //c(31)=632
        
        
        //Load 8th number
	Compare.put("627", 0x40E);
	Compare.put("628", 0x141B);
	Compare.put("629", 0x443F);
        
	Compare.put("630", 0x41B);
	Compare.put("631", 0x140E);
        
	Compare.put("632", 0x81D);
	Compare.put("633", 0x71F);
	Compare.put("634", 0x1B0B);     //c(31)=643
	Compare.put("635", 0xB1F);
        
        //Compare distance
	Compare.put("636", 0x41D);
	Compare.put("637", 0x141C);
	Compare.put("638", 0x443F);
        
	Compare.put("639", 0x61D);
	Compare.put("640", 0xA1C);
	Compare.put("641", 0x50E);
	Compare.put("642", 0x91E);

	Compare.put("643", 0x71F);
	Compare.put("644", 0x1B08);
	Compare.put("645", 0xB1F);      //(31)=651
        
        
        //Load 9th number
	Compare.put("646", 0x40F);
	Compare.put("647", 0x141B);
	Compare.put("648", 0x443F);
        
	Compare.put("649", 0x41B);
	Compare.put("650", 0x140F);
        
	Compare.put("651", 0x81D);
	Compare.put("652", 0x71F);
	Compare.put("653", 0x1B0B);     //c(31)=662
	Compare.put("654", 0xB1F);
        
        //Compare distance
	Compare.put("655", 0x41D);
	Compare.put("656", 0x141C);
	Compare.put("657", 0x443F);
        
	Compare.put("658", 0x61D);
	Compare.put("659", 0xA1C);
	Compare.put("660", 0x50F);
	Compare.put("661", 0x91E);

	Compare.put("662", 0x71F);
	Compare.put("663", 0x1B08);
	Compare.put("664", 0xB1F);      //c(31)=670
        
        
        
        //Load 10th number
	Compare.put("665", 0x410);
	Compare.put("666", 0x141B);
	Compare.put("667", 0x443F);
        
	Compare.put("668", 0x41B);
	Compare.put("669", 0x1410);
        
	Compare.put("670", 0x81D);
	Compare.put("671", 0x71F);
	Compare.put("672", 0x1B0B);     //c(31)=681
	Compare.put("673", 0xB1F);
        
        
        //Compare distance
	Compare.put("674", 0x41D);
	Compare.put("675", 0x141C);
	Compare.put("676", 0x443F);
        
	Compare.put("677", 0x61D);
	Compare.put("678", 0xA1C);
	Compare.put("679", 0x510);
	Compare.put("680", 0x91E);
	
	Compare.put("681", 0x71F);
	Compare.put("682", 0x1B08);     //c(31)=689
	Compare.put("683", 0xB1F);
        
        
        //Load 11th number
	Compare.put("684", 0x411);
	Compare.put("685", 0x141B);
	Compare.put("686", 0x443F);
        
	Compare.put("687", 0x41B);
	Compare.put("688", 0x1411);
        
	Compare.put("689", 0x81D);
	Compare.put("690", 0x71F);
	Compare.put("691", 0x1B0B);     //c(31)=700
	Compare.put("692", 0xB1F);
               
        //Compare distance
	Compare.put("693", 0x41D);
	Compare.put("694", 0x141C);
	Compare.put("695", 0x443F);
        
	Compare.put("696", 0x61D);
	Compare.put("697", 0xA1C);
	Compare.put("698", 0x511);
	Compare.put("699", 0x91E);

	Compare.put("700", 0x71F);
	Compare.put("701", 0x1B08);     //c(31)=708
	Compare.put("702", 0xB1F);
        
        
        //Load 12th number
	Compare.put("703", 0x412);
	Compare.put("704", 0x141B);
	Compare.put("705", 0x443F);
        
	Compare.put("706", 0x41B);
	Compare.put("707", 0x1412);
        
	Compare.put("708", 0x81D);
	Compare.put("709", 0x71F);
	Compare.put("710", 0x1B0B);     //c(31)=719
	Compare.put("711", 0xB1F);
        
        //Compare distance
	Compare.put("712", 0x41D);
	Compare.put("713", 0x141C);
	Compare.put("714", 0x443F);
        
	Compare.put("715", 0x61D);
	Compare.put("716", 0xA1C);
	Compare.put("717", 0x512);
	Compare.put("718", 0x91E);
	
	Compare.put("719", 0x71F);
	Compare.put("720", 0x1B08);     //c(31)=727
	Compare.put("721", 0xB1F);
        
        
        
        //Load 13th number
	Compare.put("722", 0x413);
	Compare.put("723", 0x141B);
	Compare.put("724", 0x443F);
        
	Compare.put("725", 0x41B);
	Compare.put("726", 0x1413);
        
	Compare.put("727", 0x81D);
	Compare.put("728", 0x71F);
	Compare.put("729", 0x1B0B);     //c(31)=738
	Compare.put("730", 0xB1F);
        
        //Compare distance
	Compare.put("731", 0x41D);
	Compare.put("732", 0x141C);
	Compare.put("733", 0x443F);
        
	Compare.put("734", 0x61D);
	Compare.put("735", 0xA1C);
	Compare.put("736", 0x513);
	Compare.put("737", 0x91E);
	
	Compare.put("738", 0x71F);
	Compare.put("739", 0x1B08);     //c(31)=746
	Compare.put("740", 0xB1F);
        
        
        
        //Load 14th number
	Compare.put("741", 0x414);
	Compare.put("742", 0x141B);
	Compare.put("743", 0x443F);
        
	Compare.put("744", 0x41B);
	Compare.put("745", 0x1414);
        
	Compare.put("746", 0x81D);
	Compare.put("747", 0x71F);
	Compare.put("748", 0x1B0B);     //c(31)=757
	Compare.put("749", 0xB1F);
        
        //Compare distance
	Compare.put("750", 0x41D);
	Compare.put("751", 0x141C);
	Compare.put("752", 0x443F);
        
	Compare.put("753", 0x61D);
	Compare.put("754", 0xA1C);
	Compare.put("755", 0x514);
	Compare.put("756", 0x91E);
	
	Compare.put("757", 0x71F);
	Compare.put("758", 0x1B08);     //c(31)=765
	Compare.put("759", 0xB1F);
        
        
        //Load 15th number
	Compare.put("760", 0x415);
	Compare.put("761", 0x141B);
	Compare.put("762", 0x443F);
        
	Compare.put("763", 0x41B);
	Compare.put("764", 0x1415);
        
	Compare.put("765", 0x81D);
	Compare.put("766", 0x71F);
	Compare.put("767", 0x1B0B);     //c(31)=776
	Compare.put("768", 0xB1F);
        
        //Compare distance
	Compare.put("769", 0x41D);
	Compare.put("770", 0x141C);
	Compare.put("771", 0x443F);
        
	Compare.put("772", 0x61D);
	Compare.put("773", 0xA1C);
	Compare.put("774", 0x515);
	Compare.put("775", 0x91E);
	
	Compare.put("776", 0x71F);
	Compare.put("777", 0x1B08);     //c(31)=784
	Compare.put("778", 0xB1F);
        
        
        
        //Load 16th number
	Compare.put("779", 0x416);
	Compare.put("780", 0x141B);
	Compare.put("781", 0x443F);
        
	Compare.put("782", 0x41B);
	Compare.put("783", 0x1416);
        
	Compare.put("784", 0x81D);
	Compare.put("785", 0x71F);
	Compare.put("786", 0x1B0B);     //c(31)=795
	Compare.put("787", 0xB1F);
        
        //Compare distance
	Compare.put("788", 0x41D);
	Compare.put("789", 0x141C);
	Compare.put("790", 0x443F);
        
	Compare.put("791", 0x61D);
	Compare.put("792", 0xA1C);
	Compare.put("793", 0x516);
	Compare.put("794", 0x91E);
	
	Compare.put("795", 0x71F);
	Compare.put("796", 0x1B08);     //c(31)=803
	Compare.put("797", 0xB1F);
        
        
        //Load 17th number
	Compare.put("798", 0x417);
	Compare.put("799", 0x141B);
	Compare.put("800", 0x443F);
        
	Compare.put("801", 0x41B);
	Compare.put("802", 0x1417);
        
	Compare.put("803", 0x81D);
	Compare.put("804", 0x71F);
	Compare.put("805", 0x1B0B);     //c(31)=814
	Compare.put("806", 0xB1F);
        
        //Compare distance
	Compare.put("807", 0x41D);
	Compare.put("808", 0x141C);
	Compare.put("809", 0x443F);
        
	Compare.put("810", 0x61D);
	Compare.put("811", 0xA1C);
	Compare.put("812", 0x517);
	Compare.put("813", 0x91E);
	
	Compare.put("814", 0x71F);
	Compare.put("815", 0x1B08);     //c(31)=825
	Compare.put("816", 0xB1F);
        
        
        
        //Load 18th number
	Compare.put("817", 0x418);
	Compare.put("818", 0x141B);
	Compare.put("819", 0x443F);
        
	Compare.put("820", 0x41B);
	Compare.put("821", 0x1418);
        
	Compare.put("822", 0x81D);
	Compare.put("823", 0x71F);
	Compare.put("824", 0x1B0B);     //c(31)=836
	Compare.put("825", 0xB1F);
        
        //Compare distance
	Compare.put("826", 0x41D);
	Compare.put("827", 0x141C);
	Compare.put("828", 0x443F);
        
	Compare.put("829", 0x61D);
	Compare.put("830", 0xA1C);
	Compare.put("831", 0x518);
	Compare.put("832", 0x91E);
	
	Compare.put("833", 0x71F);
	Compare.put("834", 0x1B08);     //c(31)=844
	Compare.put("835", 0xB1F);
        
        
        
        //Load 19th number
	Compare.put("836", 0x419);
	Compare.put("837", 0x141B);
	Compare.put("838", 0x443F);
        
	Compare.put("839", 0x41B);
	Compare.put("840", 0x1419);
        
	Compare.put("841", 0x81D);
	Compare.put("842", 0x71F);
	Compare.put("843", 0x1B0B);     //c(31)=855
	Compare.put("844", 0xB1F);
	
        
        //Compare distance
        Compare.put("845", 0x41D);
	Compare.put("846", 0x141C);
	Compare.put("847", 0x443F);
        
	Compare.put("848", 0x61D);
	Compare.put("849", 0xA1C);
	Compare.put("850", 0x519);
	Compare.put("851", 0x91E);
	
	Compare.put("852", 0x71F);
	Compare.put("853", 0x1B08);     //c(31)=863
	Compare.put("854", 0xB1F);
        
        
        //Load 20th number
	Compare.put("855", 0x41A);
	Compare.put("856", 0x141B);
	Compare.put("857", 0x443F);
        
	Compare.put("858", 0x41B);
	Compare.put("859", 0x141A);
        
	Compare.put("860", 0x81D);
	Compare.put("861", 0x71F);
	Compare.put("862", 0x1B0B);
	Compare.put("863", 0xB1F);
        
        //Compare distance
	Compare.put("864", 0x41D);
	Compare.put("865", 0x141C);
	Compare.put("866", 0x443F);
        
	Compare.put("867", 0x61D);
	Compare.put("868", 0xA1C);
	Compare.put("869", 0x51A);
	Compare.put("870", 0x91E);
        
	Compare.put("871", 0x71F);
    }    
    
    public static final HashMap<String, Integer> PrintResult1 = new HashMap<>();
    static{
        // presets
	PrintResult1.put("31", 1809); // start of the block (0)
	// m(30) store the number that you want to print
        
        /**
         * e.g. print number 30
         * m(50)=10 'new line'
         * m(51)=49 'ascii of '1''
         * m(52)=51 'ascii of '3''
         */
	PrintResult1.put("6", 50); // store every digit of the numbers starting from

	// program begins

	PrintResult1.put("1800", 0x7d8f);       //SRC R1<-0
	PrintResult1.put("1801", 0x190a);       //AIR R1<-R1+imm(10)
	PrintResult1.put("1802", 0x926);        //STR c(6)<-R1  c(6)=10
	PrintResult1.put("1803", 0xf26);        //LDA R3<-c(6)
	PrintResult1.put("1804", 0x1b01);       //AIR R3<-R3+imm(1)
	PrintResult1.put("1805", 0xb06);        //STR c(6)<-R3  c(6)=11
	PrintResult1.put("1806", 0x7e8f);       //SRC R2<-0
	PrintResult1.put("1807", 0x1a0a);       //AIR r2<-R2+imm(10)
	PrintResult1.put("1808", 0xc3e);        //LDA R0<-c(30)

	//Step 1
	PrintResult1.put("1809", 0x5480);       //DVD R0<-R0/10
	PrintResult1.put("1810", 0xf3f);        //LDA R3<-c(31)
	PrintResult1.put("1811", 0x1b14);       //AIR R3<-R3+imm(20)  
	PrintResult1.put("1812", 0xb1f);        //STR c(31)<-R3     c(31)=1829
	PrintResult1.put("1813", 0x283f);       //JZ R0==0?PC<-c(31):PC++
									
        //Step 2
	PrintResult1.put("1814", 0x1918);       //AIR R1<-R1+imm(24)       get ascii
	PrintResult1.put("1815", 0x1918);       //AIR R1<-R1+imm(24)
	PrintResult1.put("1816", 0x926);        //STR c(6)<-R1          c(6)=10
	PrintResult1.put("1817", 0xf26);        //LDA R3<-c(6)
	PrintResult1.put("1818", 0x1b01);       //AIR R3<-R3+imm(1)
	PrintResult1.put("1819", 0xb06);        //STR c(6)<-R3  c(6)=11
	PrintResult1.put("1820", 0xf3f);        //LDA R3<-c(31)
	PrintResult1.put("1821", 0x1f14);       //SIR R3<-R3-imm(20)
	PrintResult1.put("1822", 0xb1f);        //STR c(31)<-R3     c(31)=1809
	PrintResult1.put("1823", 0x343f);       //JMA PC=1809

	//Step 3
	PrintResult1.put("1829", 0x1918);// r1 + 48, convert to ascii
	PrintResult1.put("1830", 0x1918);
	PrintResult1.put("1831", 0x926);// store r1 into location of content of m(6)
	PrintResult1.put("1832", 0xf3f);// load r3 with content of m(31)
	PrintResult1.put("1833", 0x1b14);// AIR r3, 20
	PrintResult1.put("1834", 0xb1f);// store r3 into m(31)
	PrintResult1.put("1835", 0x7e8f);// reset r2
	PrintResult1.put("1836", 0x1a0a);// AIR r2, 10 (new line sign, use to compare)
	PrintResult1.put("1837", 0x343f);// JMA, content of m(31), means jump to (2)

	//Step 4
	PrintResult1.put("1849", 0x526);// load r1 with address of content of m(6)
	PrintResult1.put("1850", 0xf901);// print r1 to console
	PrintResult1.put("1851", 0xf3f);// load r3 with content of m(31)
	PrintResult1.put("1852", 0x1b14);// AIR r3, 20
	PrintResult1.put("1853", 0xb1f);// store r3 into m(31)
	PrintResult1.put("1854", 0x5980);// TRR r1, r2
	PrintResult1.put("1855", 0x333f);// JCC cc(3), content of m(31), mean jump to
									// (3)
	PrintResult1.put("1856", 0xf26);// load r3 with content of m(6)
	PrintResult1.put("1857", 0x1f01);// SIR r3, 1
	PrintResult1.put("1858", 0xb06);// store r3 into m(6)
	PrintResult1.put("1859", 0xf3f);// load r3 with content of m(31)
	PrintResult1.put("1860", 0x1f14);// SIR r3, 20
	PrintResult1.put("1861", 0xb1f);// store r3 into m(31)
	PrintResult1.put("1862", 0x343f);// JMA, content of m(31), means jump to (2)

	//print number
	PrintResult1.put("1869", 0);        
    }
}
