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
public class Program2 {
    public static final HashMap<String, Integer> PRE = new HashMap<>();
    static{
            PRE.put("31", 0);       //store the length of sentence
            PRE.put("30", 0);       //store the length of word
            PRE.put("29", 1);       //current sentence
            PRE.put("28", 1);       //current index of word in sentence
            PRE.put("27", 32);      //ascii of space
            PRE.put("26", 0);       //ascii of 0
            PRE.put("25", 46);      //ascii of '.'
            PRE.put("24", 0);       //R0 register
            PRE.put("23", 0);       
            PRE.put("22", -1);      //i
            PRE.put("21", 0);       //j
            PRE.put("20", 99);      //address 100, start position of sentences
            PRE.put("19", 79);      //address 80, start position of word
            PRE.put("18", 1601);    //start position of PROGRAM2_1
            PRE.put("17", 1701);    //start position of PROGRAM2_2
            PRE.put("16", 80);      //clean address 80
            PRE.put("15", 904);     //the first JMA address
            PRE.put("14", 989);     //end of program
            PRE.put("13", 951);     //the second JMA address
            PRE.put("12", 961);     //the third JMA address
            PRE.put("11", 937);     //the forth JMA address
            PRE.put("10", 969);     //the fifth JMA address
            PRE.put("9", 979);      //the sixth JMA address
            PRE.put("8", 902);      //the third JMA address
            PRE.put("7", 80);      
    }
    
    //read/store sentences
    public static final HashMap<String, Integer> PROGRAM2_1 = new HashMap<>();
    static{
            PROGRAM2_1.put("1600", 0xf34);      //LDA R3 m(20)
            
            //input 1 sentence==null?
            PROGRAM2_1.put("1601", 0xf502);     //IN R1<-input
            PROGRAM2_1.put("1602", 0xe32);      //LDA R2<-m(18)     R2=1601
            PROGRAM2_1.put("1603", 0x1a14);     //AIR R2<-R2+20     R2=1621
            PROGRAM2_1.put("1604", 0xa12);      //STR m(18)<-R2
            PROGRAM2_1.put("1605", 0x2932);     //JZ R1==0?PC<-m(18):PC++
            
            PROGRAM2_1.put("1606", 0xf901);     //OUT output<-R1
            PROGRAM2_1.put("1607", 0xc3f);      //LDA R0<-m(31)     R0=0
            PROGRAM2_1.put("1608", 0x1801);     //AIR R0<-R0+1      R0=1    1 word
            PROGRAM2_1.put("1609", 0x81f);      //STR m(31)<-R1     m(31)=1
            PROGRAM2_1.put("1610", 0xc34);      //LDA R0<-m(20)     R0=99
            PROGRAM2_1.put("1611", 0x1801);     //AIR R0<-R0+1      R0=100
            PROGRAM2_1.put("1612", 0x814);      //STR m(20)<-R0     m(20)=100
            PROGRAM2_1.put("1613", 0x934);      //STR m(100)<-R1    store input at address 100
            PROGRAM2_1.put("1614", 0xe32);      //LDA R2<-m(18)     R2=1621
            PROGRAM2_1.put("1615", 0x1e14);     //SIR R2<-R2-imm(20)R2=1601
            PROGRAM2_1.put("1616", 0xa12);      //STR m(18)<-R2     m(18)=1601
            PROGRAM2_1.put("1617", 0x3432);     //JMA m(18)         PC=1601
            
            //input 2 sentence==null?
            PROGRAM2_1.put("1621", 0x7d8f);     //SRC reset R1<-0
            PROGRAM2_1.put("1622", 0x190a);     //AIR R1<-R1+imm(10) R1=10
            PROGRAM2_1.put("1623", 0xf901);     //OUT output<-R1
            PROGRAM2_1.put("1624", 0xb14);      //STR m(20)<-R3
                       
    }
    
    //read/store 1 word need to be searched
    public static final HashMap<String, Integer> PROGRAM2_2 = new HashMap<>();
    static{
        PROGRAM2_2.put("1700", 0xf33);      //LDA R3<-m(19)     R3=79
        
        //read word
        PROGRAM2_2.put("1701", 0xf500);     //IN R1<-input
        PROGRAM2_2.put("1702", 0xe31);      //LDA R2<-m(17)     R2=1701
        PROGRAM2_2.put("1703", 0x1a14);     //AIR R2<-R2+imm(20)R2=1721
        PROGRAM2_2.put("1704", 0xa11);      //STR m(17)<-R2     m(17)=1721
        PROGRAM2_2.put("1705", 0x2931);     //JZ if input zero
        
        PROGRAM2_2.put("1706", 0xf901);     //OUT output<-R1
        //update length
        PROGRAM2_2.put("1707", 0xc3e);      //LDA R0<-m(30)     R0=0    length of word
        PROGRAM2_2.put("1708", 0x1801);     //AIR R0<-R0+imm(1) R0=1
        PROGRAM2_2.put("1709", 0x81e);      //STR m(30)<-R0
        //update word index
        PROGRAM2_2.put("1710", 0xc33);      //LDA R0<-m(19)     R0=79
        PROGRAM2_2.put("1711", 0x1801);     //AIR R0<-R0+imm(1) R0=80
        PROGRAM2_2.put("1712", 0x813);      //STR m(19)<-R0
        PROGRAM2_2.put("1713", 0x933);      //STR m(m(19))<-R1  store word at 80 I=1
        PROGRAM2_2.put("1714", 0xe31);      //LDA R2<-m(17)     R2=1721
        PROGRAM2_2.put("1715", 0x1e14);     //SIR R2<-R2-imm(20)
        PROGRAM2_2.put("1716", 0xa11);      //STR m(17)<-R2
        PROGRAM2_2.put("1717", 0x3431);     //JMA PC<-1701
        
        PROGRAM2_2.put("1721", 0x7d8f);     //SRC reset R1
        PROGRAM2_2.put("1722", 0x190a);     //AIR R1<-R1+imm(10)
        PROGRAM2_2.put("1723", 0xf901);     //OUT R1
        PROGRAM2_2.put("1724", 0xb13);      //STR m(19)<-R3
        
        
    }
    
    //search word in sentences
    public static final HashMap<String, Integer> PROGRAM2_3 = new HashMap<>();
    static{
            /**
             * R2 word pointer
             * R1 sentences pointer
             */
           PROGRAM2_3.put("900", 0x61f);        //LDA R3<-m(31)     R3=266
           PROGRAM2_3.put("901", 0x1a01);       //AIR R3<-R3+imm(1) R3=1
           PROGRAM2_3.put("902", 0x462f);       //SOB --------------------
           PROGRAM2_3.put("903", 0x342e);       //JMA PC<-m(14)     jump to end of program 989
           
           PROGRAM2_3.put("904", 0x416);        //LDR R0<-m(22)     R0=i
           PROGRAM2_3.put("905", 0x1801);       //AIR R0<-R0+imm(1)
           PROGRAM2_3.put("906", 0x816);        //STR m(22)<-R0
           PROGRAM2_3.put("907", 0x414);        //LDR R0<-m(m(20))  R0=99
           PROGRAM2_3.put("908", 0x1801);       //AIR R0<-R0+imm(1) R0=100
           PROGRAM2_3.put("909", 0x814);        //STR m(20)<-R0
           
           //whether '.'
           PROGRAM2_3.put("910", 0x534);        //LDR R1<-m(m(20))  load sentence from address 100
           PROGRAM2_3.put("911", 0x419);        //LDR R0<-m(m(25))  R0=46 load '.'
           PROGRAM2_3.put("912", 0x5840);       //TRR R1 R0         whether == '.'
           PROGRAM2_3.put("913", 0x332d);       //JCC if=='.' PC<-m(13) PC=951
           
           //whether ' '
           PROGRAM2_3.put("914", 0x41b);        //LDR R0<-m(m(27))  load ' '
           PROGRAM2_3.put("915", 0x5840);       //TRR R1 R0         whether == ' '
           PROGRAM2_3.put("916", 0x332c);       //JCC if==' ' PC<-m(12) PC=961
           
           //check word
           PROGRAM2_3.put("917", 0x627);        //LDR R2<-m(m(80)) first capital of word
           PROGRAM2_3.put("918", 0x5980);       //TRR R1 R2
           PROGRAM2_3.put("919", 0x332b);       //JCC if==captial PC=937
           
           //not equal
           //reset i
           PROGRAM2_3.put("920", 0x416);        //LDR R0<-m(m(22))  R0=i
           PROGRAM2_3.put("921", 0x1415);       //SMR R0<-i-j
           PROGRAM2_3.put("922", 0x816);        //STR m(22)<-R0
           //reset index of sentences
           PROGRAM2_3.put("923", 0x414);        //LDR R0<-m(m(20))  R0=100
           PROGRAM2_3.put("924", 0x1415);       //SMR R0<-100-j
           PROGRAM2_3.put("925", 0x814);        //STR m(20)<-100-j
           
           PROGRAM2_3.put("926", 0x419);        //LDR R0<-m(m(25))  R0='.'
           PROGRAM2_3.put("927", 0x5840);       //TRR R1 R0
           PROGRAM2_3.put("928", 0x332a);       //JCC if==captial PC=969
           
           PROGRAM2_3.put("929", 0x41b);        //LDR R0<-m(m(27))  R0=' '
           PROGRAM2_3.put("930", 0x5840);       //TRR R1 R0         whether == ' '
           PROGRAM2_3.put("931", 0x3329);       //JCC if==' ' PC<-m(9) PC=979
           //reset j
           PROGRAM2_3.put("932", 0x41a);        //LDR R0<-m(m(26))  R0=0
           PROGRAM2_3.put("933", 0x815);        //STR m(21)<-R0     j=0
           
           //return check
           PROGRAM2_3.put("934", 0x410);        //LDR R0<-m(m(16))  R0=80
           PROGRAM2_3.put("935", 0x807);        //STR m(7)<-R0
           PROGRAM2_3.put("936", 0x3428);       //JMA PC<-902
           
           //begin check second captial..
           PROGRAM2_3.put("937", 0x415);        //LDR R0<-m(21)     R0=j
           PROGRAM2_3.put("938", 0x918);        //STR m(24)<-R1
           PROGRAM2_3.put("939", 0x51e);        //LDR R1<-m(30)
           PROGRAM2_3.put("940", 0x1d01);       //SIR R1<-R1-imm(1)
           PROGRAM2_3.put("941", 0x5840);       //TRR R1 R0
           PROGRAM2_3.put("942", 0x518);        //STR m(24)<-R1
           PROGRAM2_3.put("943", 0x332e);       //JCC R1==R0?989:PC++
           PROGRAM2_3.put("944", 0x415);        //LDR R0<-m(21)     R0=j
           PROGRAM2_3.put("945", 0x1801);       //AIR R0<-R0+imm(1) j increase
           PROGRAM2_3.put("946", 0x815);        //STR m(21)<-R0
           PROGRAM2_3.put("947", 0x407);        //LDR R0<-m(7)
           PROGRAM2_3.put("948", 0x1801);       //AIR R0<-R0+imm(1)
           PROGRAM2_3.put("949", 0x807);        //STR m(7)<-R0
           PROGRAM2_3.put("950", 0x3428);       //JMA PC<-902
           
           PROGRAM2_3.put("951", 0x41d);        //LDR R0<-m(29)
           PROGRAM2_3.put("952", 0x1801);       //AIR R0<-R0+imm(1)
           PROGRAM2_3.put("953", 0x81d);        //STR m(29)<-R0
           PROGRAM2_3.put("954", 0x41a);        //LDR R0<-m(26)
           PROGRAM2_3.put("955", 0x81c);        //STR m(28)<-R0     set m(28)=0
           PROGRAM2_3.put("956", 0x40d);        //LDR R0<-m(13)     R0=951
           PROGRAM2_3.put("957", 0x1c1f);       //SIR R0<-R0-imm(31) = 920
           PROGRAM2_3.put("958", 0x1c06);       //SIR R0<-R0-imm(6) = 914
           PROGRAM2_3.put("959", 0x806);        //STR m(6)<-R0
           PROGRAM2_3.put("960", 0x3426);       //JMA PC<-914
           
           PROGRAM2_3.put("961", 0x41c);        //LDR R0<-m(28) 
           PROGRAM2_3.put("962", 0x1801);       //AIR R0<-R0+imm(1)
           PROGRAM2_3.put("963", 0x81c);        //STR m(28)<-R0
           PROGRAM2_3.put("964", 0x40d);        //LDR R0<-m(12)     R0=961
           PROGRAM2_3.put("965", 0x1c1f);       //SIR R0<-R0-imm(31) = 930
           PROGRAM2_3.put("966", 0x1c0d);       //SIR R0<-R0-imm(13) = 917
           PROGRAM2_3.put("967", 0x806);        //STR m(6)<-R0
           PROGRAM2_3.put("968", 0x3426);       //JMA PC<-917
           
           PROGRAM2_3.put("969", 0x40a);        //LDR R0<-m(10)     R0=969
           PROGRAM2_3.put("970", 0x1c1f);       //SIR R0<-R0-imm(31) = 938
           PROGRAM2_3.put("971", 0x1c09);       //SIR R0<-R0-imm(9) = 929
           PROGRAM2_3.put("972", 0x806);        //STR m(6)<-R0
           PROGRAM2_3.put("973", 0x415);        //LDR R0<-m(21)
           PROGRAM2_3.put("974", 0x2826);       //JZ j==0 PC<-m(6)
           
           PROGRAM2_3.put("975", 0x41d);        //LDR R0<-m(29)
           PROGRAM2_3.put("976", 0x1c01);       //SIR R0<-R0-imm(1)
           PROGRAM2_3.put("977", 0x81d);        //STR m(29)<-R0
           PROGRAM2_3.put("978", 0x3426);       //JMA PC<-m(29)
           
           PROGRAM2_3.put("979", 0x409);        //LDR R0<-m(9) 979
           PROGRAM2_3.put("980", 0x1c1f);       //SIR R0<-R0-imm(31) = 948
           PROGRAM2_3.put("981", 0x1c10);       //SIR R0<-R0-imm(16) = 932
           PROGRAM2_3.put("982", 0x806);        //STR m(6)<-R0
           PROGRAM2_3.put("983", 0x415);        //LDR R0<-m(21)
           PROGRAM2_3.put("984", 0x2826);       //JZ
           
           PROGRAM2_3.put("985", 0x41c);        //LDR R0<-m(28)
           PROGRAM2_3.put("986", 0x1c01);       //SIR R0<-R0-imm(1)
           PROGRAM2_3.put("987", 0x81c);        //STR m(28)<-R0
           PROGRAM2_3.put("988", 0x3426);       //JMA
           PROGRAM2_3.put("989", 0x41d);        //LDR R0<-m(29)
           
            
    }
    
    //print m(28)
    public static final HashMap<String, Integer> PrintResult1 = new HashMap<>();
    static{
                PrintResult1.put("31", 1009); // start of the block (0)
		// m(30) store the number that you want to print
		PrintResult1.put("6", 50); // store every digit of the numbers starting from

		// program begins

		PrintResult1.put("1000", 0x7d8f); // reset r1
		PrintResult1.put("1001", 0x190a); // AIR r1, 10
		PrintResult1.put("1002", 0x926); // store r1 into location of content of m(6)
		PrintResult1.put("1003", 0xf26); // load r3 with content of m(6)
		PrintResult1.put("1004", 0x1b01); // AIR r3, 1
		PrintResult1.put("1005", 0xb06); // store r3 into m(6)
		PrintResult1.put("1006", 0x7e8f); // reset r2
		PrintResult1.put("1007", 0x1a0a); // AIR r2, 10
		PrintResult1.put("1008", 0xc3c); // load r0 with content of m(28)

		// (0)
		PrintResult1.put("1009", 0x5480); // DVD r0, r2
		PrintResult1.put("1010", 0xf3f);// load r3 with content of m(31)
		PrintResult1.put("1011", 0x1b14);// AIR r3, 20
		PrintResult1.put("1012", 0xb1f);// store r3 into m(31)
		PrintResult1.put("1013", 0x283f);// JZ r0, content of m(31), means if r0 == 0
									// jump to (1)
		PrintResult1.put("1014", 0x1918);// r1 + 48, convert to ascii
		PrintResult1.put("1015", 0x1918);
		PrintResult1.put("1016", 0x926);// store r1 into location of content of m(6)
		PrintResult1.put("1017", 0xf26);// load r3 with content of m(6)
		PrintResult1.put("1018", 0x1b01);// AIR r3, 1
		PrintResult1.put("1019", 0xb06);// store r3 into m(6)
		PrintResult1.put("1020", 0xf3f);// load r3 with content of m(31)
		PrintResult1.put("1021", 0x1f14);// SIR r3, 20
		PrintResult1.put("1022", 0xb1f);// store r3 into m(31)
		PrintResult1.put("1023", 0x343f);// JMA, content of m(31), means jump to (0)

		// (1) r0 == 0, means has reach the highest digit of the number
		PrintResult1.put("1029", 0x1918);// r1 + 48, convert to ascii
		PrintResult1.put("1030", 0x1918);
		PrintResult1.put("1031", 0x926);// store r1 into location of content of m(6)
		PrintResult1.put("1032", 0xf3f);// load r3 with content of m(31)
		PrintResult1.put("1033", 0x1b14);// AIR r3, 20
		PrintResult1.put("1034", 0xb1f);// store r3 into m(31)
		PrintResult1.put("1035", 0x7e8f);// reset r2
		PrintResult1.put("1036", 0x1a0a);// AIR r2, 10 (new line sign, use to compare)
		PrintResult1.put("1037", 0x343f);// JMA, content of m(31), means jump to (2)

		// (2) print the digit one by one
		PrintResult1.put("1049", 0x526);// load r1 with address of content of m(6)
		PrintResult1.put("1050", 0xf901);// print r1 to console
		PrintResult1.put("1051", 0xf3f);// load r3 with content of m(31)
		PrintResult1.put("1052", 0x1b14);// AIR r3, 20
		PrintResult1.put("1053", 0xb1f);// store r3 into m(31)
		PrintResult1.put("1054", 0x5980);// TRR r1, r2
		PrintResult1.put("1055", 0x333f);// JCC cc(3), content of m(31), mean jump to
									// (3)
		PrintResult1.put("1056", 0xf26);// load r3 with content of m(6)
		PrintResult1.put("1057", 0x1f01);// SIR r3, 1
		PrintResult1.put("1058", 0xb06);// store r3 into m(6)
		PrintResult1.put("1059", 0xf3f);// load r3 with content of m(31)
		PrintResult1.put("1060", 0x1f14);// SIR r3, 20
		PrintResult1.put("1061", 0xb1f);// store r3 into m(31)
		PrintResult1.put("1062", 0x343f);// JMA, content of m(31), means jump to (2)

		// (3) finish printing the numbers
		PrintResult1.put("1069", 0);
    }
    
    //print m(29)
    public static final HashMap<String, Integer> PrintResult2 = new HashMap<>();
    static{
		PrintResult2.put("31", 1109); // start of the block (0)
		// m(30) store the number that you want to print
		PrintResult2.put("6", 50); // store every digit of the numbers starting from
                
		PrintResult2.put("1100", 0x7d8f); // reset r1
		PrintResult2.put("1101", 0x190a); // AIR r1, 10
		PrintResult2.put("1102", 0x926); // store r1 into location of content of m(6)
		PrintResult2.put("1103", 0xf26); // load r3 with content of m(6)
		PrintResult2.put("1104", 0x1b01); // AIR r3, 1
		PrintResult2.put("1105", 0xb06); // store r3 into m(6)
		PrintResult2.put("1106", 0x7e8f); // reset r2
		PrintResult2.put("1107", 0x1a0a); // AIR r2, 10
		PrintResult2.put("1108", 0xc3d); // load r0 with content of m(29)

		// (0)
		PrintResult2.put("1109", 0x5480); // DVD r0, r2
		PrintResult2.put("1110", 0xf3f);// load r3 with content of m(31)
		PrintResult2.put("1111", 0x1b14);// AIR r3, 20
		PrintResult2.put("1112", 0xb1f);// store r3 into m(31)
		PrintResult2.put("1113", 0x283f);// JZ r0, content of m(31), means if r0 == 0
									// jump to (1)
		PrintResult2.put("1114", 0x1918);// r1 + 48, convert to ascii
		PrintResult2.put("1115", 0x1918);
		PrintResult2.put("1116", 0x926);// store r1 into location of content of m(6)
		PrintResult2.put("1117", 0xf26);// load r3 with content of m(6)
		PrintResult2.put("1118", 0x1b01);// AIR r3, 1
		PrintResult2.put("1119", 0xb06);// store r3 into m(6)
		PrintResult2.put("1120", 0xf3f);// load r3 with content of m(31)
		PrintResult2.put("1121", 0x1f14);// SIR r3, 20
		PrintResult2.put("1122", 0xb1f);// store r3 into m(31)
		PrintResult2.put("1123", 0x343f);// JMA, content of m(31), means jump to (0)
		// (1) r0 == 0, means has reach the highest digit of the number
		PrintResult2.put("1129", 0x1918);// r1 + 48, convert to ascii
		PrintResult2.put("1130", 0x1918);
		PrintResult2.put("1131", 0x926);// store r1 into location of content of m(6)
		PrintResult2.put("1132", 0xf3f);// load r3 with content of m(31)
		PrintResult2.put("1133", 0x1b14);// AIR r3, 20
		PrintResult2.put("1134", 0xb1f);// store r3 into m(31)
		PrintResult2.put("1135", 0x7e8f);// reset r2
		PrintResult2.put("1136", 0x1a0a);// AIR r2, 10 (new line sign, use to compare)
		PrintResult2.put("1137", 0x343f);// JMA, content of m(31), means jump to (2)
		// (2) print the digit one by one
		PrintResult2.put("1149", 0x526);// load r1 with address of content of m(6)
		PrintResult2.put("1150", 0xf901);// print r1 to console
		PrintResult2.put("1151", 0xf3f);// load r3 with content of m(31)
		PrintResult2.put("1152", 0x1b14);// AIR r3, 20
		PrintResult2.put("1153", 0xb1f);// store r3 into m(31)
		PrintResult2.put("1154", 0x5980);// TRR r1, r2
		PrintResult2.put("1155", 0x333f);// JCC cc(3), content of m(31), mean jump to
									// (3)
		PrintResult2.put("1156", 0xf26);// load r3 with content of m(6)
		PrintResult2.put("1157", 0x1f01);// SIR r3, 1
		PrintResult2.put("1158", 0xb06);// store r3 into m(6)
		PrintResult2.put("1159", 0xf3f);// load r3 with content of m(31)
		PrintResult2.put("1160", 0x1f14);// SIR r3, 20
		PrintResult2.put("1161", 0xb1f);// store r3 into m(31)
		PrintResult2.put("1162", 0x343f);// JMA, content of m(31), means jump to (2)

		// (3) finish printing the numbers
		PrintResult2.put("1169", 0);        
    }
}
