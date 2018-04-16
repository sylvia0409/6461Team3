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
public class ProgramFloatSub {
    
    public static final HashMap<String, Integer> StoreFloat = new HashMap<>();
    static{
        StoreFloat.put("300", 0x7c8f);      //SRC set R0=0
        StoreFloat.put("301", 0xf3e);       //LDA R3<-c(30)
        StoreFloat.put("302", 0xe26);       //LDA R2<-c(6)
        StoreFloat.put("303", 0xf500);      //IN  R1<-input
        StoreFloat.put("304", 0x5980);      //TRR R3==R0 ? cc(3)=1:cc(3)=0
        StoreFloat.put("305", 0xe3c);       //LDA R2<-c(28)
        StoreFloat.put("306", 0x1a14);      //AIR R2<-R2+imm(20)
        StoreFloat.put("307", 0xa1c);       //STR c(28)<-R2
        StoreFloat.put("308", 0x333c);      //JCC cc(3)==1 ? PC<-c(28):PC<-PC+1

        StoreFloat.put("309", 0xe3c);       //LDA R2<-c(28)
        StoreFloat.put("310", 0x1a14);      //AIR R2<-R2+imm(20)
        StoreFloat.put("311", 0xa1c);       //STR c(28)<-R2
        StoreFloat.put("312", 0x343c);      //JMA PC<-c(28)
        
        StoreFloat.put("322", 0x83d);       //STR c(29)<-R0
        StoreFloat.put("323", 0x7c8f);      //SRC R0<-0
        StoreFloat.put("324", 0x180a);      //AIR R0<-R0+imm(10)
        StoreFloat.put("325", 0xf801);      //OUT output<-R0
        StoreFloat.put("326", 0xd3d);       //LDA R1<-c(29)
        StoreFloat.put("327", 0x1901);      //AIR R1<-R1+imm(1)
        StoreFloat.put("328", 0x91d);       //STR c(29)<-R1
        StoreFloat.put("329", 0x7c8f);      //SRC R0<-0
        StoreFloat.put("330", 0xe3c);       //LDA R2<-c(28)
        StoreFloat.put("331", 0x1a14);      //AIR R2<-R2+imm(20)
        StoreFloat.put("332", 0x1a14);      //AIR R2<-R2+imm(20)
        StoreFloat.put("333", 0xa1c);       //STR c(28)<-R2
        StoreFloat.put("334", 0x343c);      //JMA PC<-c(28)
        
        
        StoreFloat.put("342", 0x83D);       //STR c(29)<-R0
        StoreFloat.put("343", 0x103D);      //AMR R0<-R0+c(29)
        StoreFloat.put("344", 0x103D);      //AMR R0<-R0+c(29)
        StoreFloat.put("345", 0x103D);      //AMR R0<-R0+c(29)
        StoreFloat.put("346", 0x103D);      //AMR R0<-R0+c(29)
        StoreFloat.put("347", 0x103D);      //AMR R0<-R0+c(29)
        StoreFloat.put("348", 0x103D);      //AMR R0<-R0+c(29)
        StoreFloat.put("349", 0x103D);      //AMR R0<-R0+c(29)
        StoreFloat.put("350", 0x103D);      //AMR R0<-R0+c(29)
        StoreFloat.put("351", 0x103D);      //AMR R0<-R0+c(29)
        StoreFloat.put("352", 0xf901);      //OUT output<-R1
        StoreFloat.put("353", 0x1d18);      //SIR R1<-R1-imm(24)
        StoreFloat.put("354", 0x1d18);      //SIR R1<-R1-imm(24)
        StoreFloat.put("355", 0x93d);       //STR c(29)<-R1
        StoreFloat.put("356", 0x103d);      //AMR R0<-R0+c(29)
        StoreFloat.put("357", 0xe3c);       //LDA R2<-c(28)
        StoreFloat.put("358", 0x1e14);      //SIR R2-imm(20)
        StoreFloat.put("359", 0x1e14);      //SIR R2-imm(20)
        StoreFloat.put("360", 0xa1c);       //STR c(28)<-R2
        StoreFloat.put("361", 0x343c);      //JMA PC<-c(28)
        StoreFloat.put("362", 0xe3c);       //LDA R2<-c(28)
        StoreFloat.put("363", 0x1e14);      //SIR R2<-R2-imm(20)
        StoreFloat.put("364", 0x1e14);      //SIR R2<-R2-imm(20)
        StoreFloat.put("365", 0x1e14);      //SIR R2<-R2-imm(20)
        StoreFloat.put("366", 0xa1c);       //STR c(28)<-R2
        StoreFloat.put("367", 0x433c);      //SOB R3-1>0?PC<-c(28):PC++
        StoreFloat.put("368", 0x7c8f);      //SRC R0<-0
        StoreFloat.put("369", 0x1801);      //AIR R0<-R0+imm(1)
        StoreFloat.put("370", 0x81e);       //STR c(30)<-R0 
    }    
    
    public static final HashMap<String, Integer> Read_FloatSub = new HashMap<>();
    static{

        
        Read_FloatSub.put("300", 0xc81c);   //LDFR Fr0<-c(28)
        Read_FloatSub.put("302", 0x881e);   //FSUB Fr0<Fr0-c(30)
        Read_FloatSub.put("303", 0xcc1a);   //STFR c(26)<-Fr0
        Read_FloatSub.put("304", 0x0c1a);   //LDA R0<-c(26)
        Read_FloatSub.put("305", 0xf801);   //OUT  R0
        
        
    }    
    
}
