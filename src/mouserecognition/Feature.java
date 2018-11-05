/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mouserecognition;

import java.util.ArrayList;
import java.util.Arrays;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

   

/**
 *
 * @author Denes
 */
public class Feature {
    public static final ArrayList<String> att = new ArrayList<String>(Arrays.asList("Euklideszi"	,"Xtav"	,"Ytav"	,"Pontonkentitav"	,"Sebesseg"	,"Xsebesseg"	,"Ysebesseg"	,"Atlagsebesseg"	,"Xatlagszabesseg"	,"Yatlagsebesseg"	,"Gyorsulas"	,"Xgyorsulas"	,"Ygyorsulas"	,"Pontonkentigyorsulas"	,"Xpontonkentigyosulas"	,"Ypontonkentigyorsulas"	,"jerkue"	,"jerkpontonkenti"	,"ossszszog"	,"atlagszogseb"	,"mozgaspontok"	,"milyen"	,"melyikgomb"));
   private double Euklideszi;	
   private double Xtav;	
   private double Ytav;	
   private double Pontonkentitav;	
   private double Sebesseg;	 
   private double Xsebesseg;	
   private double Ysebesseg;	
   private double Atlagsebesseg;	
   private double Xatlagszabesseg;	
   private double Yatlagsebesseg;	
   private double Gyorsulas;	
   private double Xgyorsulas;	
   private double Ygyorsulas;	
   private double Pontonkentigyorsulas;	
   private double Xpontonkentigyosulas;	
   private double Ypontonkentigyorsulas;	
   private double jerkue;	
   private double jerkpontonkenti;	
   private double ossszszog;	
   private double atlagszogseb;	
   private int mozgaspontok;	
   private int milyen;	
   private int melyikgomb;
   private double[] values;

    @Override
    public String toString() {
        return "Feature{" + "Euklideszi=" + Euklideszi + ", Xtav=" + Xtav + ", Ytav=" + Ytav + ", Pontonkentitav=" + Pontonkentitav + ", Sebesseg=" + Sebesseg + ", Xsebesseg=" + Xsebesseg + ", Ysebesseg=" + Ysebesseg + ", Atlagsebesseg=" + Atlagsebesseg + ", Xatlagszabesseg=" + Xatlagszabesseg + ", Yatlagsebesseg=" + Yatlagsebesseg + ", Gyorsulas=" + Gyorsulas + ", Xgyorsulas=" + Xgyorsulas + ", Ygyorsulas=" + Ygyorsulas + ", Pontonkentigyorsulas=" + Pontonkentigyorsulas + ", Xpontonkentigyosulas=" + Xpontonkentigyosulas + ", Ypontonkentigyorsulas=" + Ypontonkentigyorsulas + ", jerkue=" + jerkue + ", jerkpontonkenti=" + jerkpontonkenti + ", ossszszog=" + ossszszog + ", atlagszogseb=" + atlagszogseb + ", mozgaspontok=" + mozgaspontok + ", milyen=" + milyen + ", melyikgomb=" + melyikgomb + '}';
    }

    
    public Instance getInstance(){
//        ArrayList<Attribute> attr = new ArrayList<>();
//        for(int i =0;i<att.size();++i){
//            attr.add(new Attribute(att.get(i)));
//        }
          //this.values =
          return new DenseInstance(1.0,this.values);
    }
   
    
    
    public Feature() {
        this.values = new double[24];
    }

    public Feature(double Euklideszi, double Xtav, double Ytav, double Pontonkentitav, double Sebesseg, double Xsebesseg, double Ysebesseg, double Atlagsebesseg, double Xatlagszabesseg, double Yatlagsebesseg, double Gyorsulas, double Xgyorsulas, double Ygyorsulas, double Pontonkentigyorsulas, double Xpontonkentigyosulas, double Ypontonkentigyorsulas, double jerkue, double jerkpontonkenti, double ossszszog, double atlagszogseb, int mozgaspontok, int milyen, int melyikgomb) {
        this.Euklideszi = Euklideszi;
        this.Xtav = Xtav;
        this.Ytav = Ytav;
        this.Pontonkentitav = Pontonkentitav;
        this.Sebesseg = Sebesseg;
        this.Xsebesseg = Xsebesseg;
        this.Ysebesseg = Ysebesseg;
        this.Atlagsebesseg = Atlagsebesseg;
        this.Xatlagszabesseg = Xatlagszabesseg;
        this.Yatlagsebesseg = Yatlagsebesseg;
        this.Gyorsulas = Gyorsulas;
        this.Xgyorsulas = Xgyorsulas;
        this.Ygyorsulas = Ygyorsulas;
        this.Pontonkentigyorsulas = Pontonkentigyorsulas;
        this.Xpontonkentigyosulas = Xpontonkentigyosulas;
        this.Ypontonkentigyorsulas = Ypontonkentigyorsulas;
        this.jerkue = jerkue;
        this.jerkpontonkenti = jerkpontonkenti;
        this.ossszszog = ossszszog;
        this.atlagszogseb = atlagszogseb;
        this.mozgaspontok = mozgaspontok;
        this.milyen = milyen;
        this.melyikgomb = melyikgomb;
    }

    public double getEuklideszi() {
        return Euklideszi;
    }

    public void setEuklideszi(double Euklideszi) {
        this.Euklideszi = Euklideszi;
        this.values[0]=Euklideszi;
    }

    public double getXtav() {
        return Xtav;
    }

    public void setXtav(double Xtav) {
        this.Xtav = Xtav;
        this.values[1]=Xtav;
    }

    public double getYtav() {
        return Ytav;
    }

    public void setYtav(double Ytav) {
        this.Ytav = Ytav;
        this.values[2]=Ytav;
    }

    public double getPontonkentitav() {
        return Pontonkentitav;
    }

    public void setPontonkentitav(double Pontonkentitav) {
        this.Pontonkentitav = Pontonkentitav;
        this.values[3]=Pontonkentitav;
    }

    public double getSebesseg() {
        return Sebesseg;
    }

    public void setSebesseg(double Sebesseg) {
        this.Sebesseg = Sebesseg;
        this.values[4]= Sebesseg;
    }

    public double getXsebesseg() {
        return Xsebesseg;
    }

    public void setXsebesseg(double Xsebesseg) {
        this.Xsebesseg = Xsebesseg;
        this.values[5]=Xsebesseg;
    }

    public double getYsebesseg() {
        return Ysebesseg;
    }

    public void setYsebesseg(double Ysebesseg) {
        this.Ysebesseg = Ysebesseg;
        this.values[6]=Ysebesseg;
    }

    public double getAtlagsebesseg() {
        return Atlagsebesseg;
    }

    public void setAtlagsebesseg(double Atlagsebesseg) {
        this.Atlagsebesseg = Atlagsebesseg;
        this.values[7]=Atlagsebesseg;
    }

    public double getXatlagszabesseg() {
        return Xatlagszabesseg;
    }

    public void setXatlagszabesseg(double Xatlagszabesseg) {
        this.Xatlagszabesseg = Xatlagszabesseg;
        this.values[8]=Xatlagszabesseg;
    }

    public double getYatlagsebesseg() {
        return Yatlagsebesseg;
    }

    public void setYatlagsebesseg(double Yatlagsebesseg) {
        this.Yatlagsebesseg = Yatlagsebesseg;
        this.values[9]=Yatlagsebesseg;
    }

    public double getGyorsulas() {
        return Gyorsulas;
    }

    public void setGyorsulas(double Gyorsulas) {
        this.Gyorsulas = Gyorsulas;
        this.values[10]=Gyorsulas;
    }

    public double getXgyorsulas() {
        return Xgyorsulas;
    }

    public void setXgyorsulas(double Xgyorsulas) {
        this.Xgyorsulas = Xgyorsulas;
        this.values[11]=Xgyorsulas;
    }

    public double getYgyorsulas() {
        return Ygyorsulas;
    }

    public void setYgyorsulas(double Ygyorsulas) {
        this.Ygyorsulas = Ygyorsulas;
        this.values[12]=Ygyorsulas;
    }

    public double getPontonkentigyorsulas() {
        return Pontonkentigyorsulas;
    }

    public void setPontonkentigyorsulas(double Pontonkentigyorsulas) {
        this.Pontonkentigyorsulas = Pontonkentigyorsulas;
        this.values[13]=Pontonkentigyorsulas;
    }

    public double getXpontonkentigyosulas() {
        return Xpontonkentigyosulas;
    }

    public void setXpontonkentigyosulas(double Xpontonkentigyosulas) {
        this.Xpontonkentigyosulas = Xpontonkentigyosulas;
        this.values[14]=Xpontonkentigyosulas;
    }

    public double getYpontonkentigyorsulas() {
        return Ypontonkentigyorsulas;
    }

    public void setYpontonkentigyorsulas(double Ypontonkentigyorsulas) {
        this.Ypontonkentigyorsulas = Ypontonkentigyorsulas;
        this.values[15]=Ypontonkentigyorsulas;
    }

    public double getJerkue() {
        return jerkue;
    }

    public void setJerkue(double jerkue) {
        this.jerkue = jerkue;
        this.values[16]=jerkue;
    }

    public double getJerkpontonkenti() {
        return jerkpontonkenti;
    }

    public void setJerkpontonkenti(double jerkpontonkenti) {
        this.jerkpontonkenti = jerkpontonkenti;
        this.values[17]=jerkpontonkenti;
    }

    public double getOssszszog() {
        return ossszszog;
    }

    public void setOssszszog(double ossszszog) {
        this.ossszszog = ossszszog;
        this.values[18]=ossszszog;
    }

    public double getAtlagszogseb() {
        return atlagszogseb;
    }

    public void setAtlagszogseb(double atlagszogseb) {
        this.atlagszogseb = atlagszogseb;
        this.values[19]=atlagszogseb;
    }

    public int getMozgaspontok() {
        return mozgaspontok;
    }

    public void setMozgaspontok(int mozgaspontok) {
        this.mozgaspontok = mozgaspontok;
        this.values[20]=(double)mozgaspontok;
    }

    public int getMilyen() {
        return milyen;
    }

    public void setMilyen(int milyen) {
        this.milyen = milyen;
        this.values[21]=(double)milyen;
    }

    public int getMelyikgomb() {
        return melyikgomb;
    }

    public void setMelyikgomb(int melyikgomb) {
        this.melyikgomb = melyikgomb;
        this.values[22]=(double)melyikgomb;
    }
    
    

    
}
