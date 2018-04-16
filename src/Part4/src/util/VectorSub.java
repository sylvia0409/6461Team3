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
public class VectorSub {
    
    public static final HashMap<String, Integer> StoreVector = new HashMap<>();
    static{
        StoreVector.put("300", 0x7c8f);      //SRC set R0=0
        StoreVector.put("301", 0xf3e);       //LDA R3<-c(30)
        StoreVector.put("302", 0xe26);       //LDA R2<-c(6)
        StoreVector.put("303", 0xf500);      //IN  R1<-input
        StoreVector.put("304", 0x5980);      //TRR R3==R0 ? cc(3)=1:cc(3)=0
        StoreVector.put("305", 0xe3c);       //LDA R2<-c(28)
        StoreVector.put("306", 0x1a14);      //AIR R2<-R2+imm(20)
        StoreVector.put("307", 0xa1c);       //STR c(28)<-R2
        StoreVector.put("308", 0x333c);      //JCC cc(3)==1 ? PC<-c(28):PC<-PC+1

        StoreVector.put("309", 0xe3c);       //LDA R2<-c(28)
        StoreVector.put("310", 0x1a14);      //AIR R2<-R2+imm(20)
        StoreVector.put("311", 0xa1c);       //STR c(28)<-R2
        StoreVector.put("312", 0x343c);      //JMA PC<-c(28)
        
        StoreVector.put("322", 0x83d);       //STR c(29)<-R0
        StoreVector.put("323", 0x7c8f);      //SRC R0<-0
        StoreVector.put("324", 0x180a);      //AIR R0<-R0+imm(10)
        StoreVector.put("325", 0xf801);      //OUT output<-R0
        StoreVector.put("326", 0xd3d);       //LDA R1<-c(29)
        StoreVector.put("327", 0x1901);      //AIR R1<-R1+imm(1)
        StoreVector.put("328", 0x91d);       //STR c(29)<-R1
        StoreVector.put("329", 0x7c8f);      //SRC R0<-0
        StoreVector.put("330", 0xe3c);       //LDA R2<-c(28)
        StoreVector.put("331", 0x1a14);      //AIR R2<-R2+imm(20)
        StoreVector.put("332", 0x1a14);      //AIR R2<-R2+imm(20)
        StoreVector.put("333", 0xa1c);       //STR c(28)<-R2
        StoreVector.put("334", 0x343c);      //JMA PC<-c(28)
        
        
        StoreVector.put("342", 0x83D);       //STR c(29)<-R0
        StoreVector.put("343", 0x103D);      //AMR R0<-R0+c(29)
        StoreVector.put("344", 0x103D);      //AMR R0<-R0+c(29)
        StoreVector.put("345", 0x103D);      //AMR R0<-R0+c(29)
        StoreVector.put("346", 0x103D);      //AMR R0<-R0+c(29)
        StoreVector.put("347", 0x103D);      //AMR R0<-R0+c(29)
        StoreVector.put("348", 0x103D);      //AMR R0<-R0+c(29)
        StoreVector.put("349", 0x103D);      //AMR R0<-R0+c(29)
        StoreVector.put("350", 0x103D);      //AMR R0<-R0+c(29)
        StoreVector.put("351", 0x103D);      //AMR R0<-R0+c(29)
        StoreVector.put("352", 0xf901);      //OUT output<-R1
        StoreVector.put("353", 0x1d18);      //SIR R1<-R1-imm(24)
        StoreVector.put("354", 0x1d18);      //SIR R1<-R1-imm(24)
        StoreVector.put("355", 0x93d);       //STR c(29)<-R1
        StoreVector.put("356", 0x103d);      //AMR R0<-R0+c(29)
        StoreVector.put("357", 0xe3c);       //LDA R2<-c(28)
        StoreVector.put("358", 0x1e14);      //SIR R2-imm(20)
        StoreVector.put("359", 0x1e14);      //SIR R2-imm(20)
        StoreVector.put("360", 0xa1c);       //STR c(28)<-R2
        StoreVector.put("361", 0x343c);      //JMA PC<-c(28)
        StoreVector.put("362", 0xe3c);       //LDA R2<-c(28)
        StoreVector.put("363", 0x1e14);      //SIR R2<-R2-imm(20)
        StoreVector.put("364", 0x1e14);      //SIR R2<-R2-imm(20)
        StoreVector.put("365", 0x1e14);      //SIR R2<-R2-imm(20)
        StoreVector.put("366", 0xa1c);       //STR c(28)<-R2
        StoreVector.put("367", 0x433c);      //SOB R3-1>0?PC<-c(28):PC++
        StoreVector.put("368", 0x7c8f);      //SRC R0<-0
        StoreVector.put("369", 0x1801);      //AIR R0<-R0+imm(1)
        StoreVector.put("370", 0x81e);       //STR c(30)<-R0 
    }    
    
    public static final HashMap<String, Integer> VectorAdd = new HashMap<>();
    static{

        /***
         * length of vector store in c(30)
         * start address of vectore in c(28)
         */
        
        VectorAdd.put("300", 0xc81e);   //LDFR FR0<-c(30)
        VectorAdd.put("301", 0x909c);   //VSUB fr0, 1, c(c(28))
        VectorAdd.put("302", 0x043c);   //LDR R0<-c(c(28))
        VectorAdd.put("303", 0xf801);   //OUT  R0
        
        
    }    
    
}
