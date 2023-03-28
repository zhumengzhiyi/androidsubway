package com.example.subway2;

import java.util.ArrayList;
import java.util.List;

public class action {
    public static String[] chaxianlu(String station) {


        List<Bean1> list = new ArrayList<Bean1>();
        list=new Dao1().selectByName(station);
        String[] nol= new String[8];
        int i=0;
        for(Bean1 s:list) {
            System.out.println("遍历");
            System.out.println(s.getID()+",车站:"+s.getName()+"路线"+s.getNol());
            nol[i++]=s.getNol();
        }
        return nol;
    }

    //由线路查车站
    public static List<Bean1> chastation(String nol,String stStation,String enStation){
        List<Bean1> list = new ArrayList<Bean1>();
        list=new Dao1().selectByNol(nol);

        for(Bean1 s:list) {
            System.out.println("遍历");
            System.out.println(s.getID()+",车站:"+s.getName()+"路线"+s.getNol());

        }
        return list;


    }



    //换站查询。查找换站路线与换站点
    public static String[]  Huanzhanlu(String qishizhan){

        String[] luxian1=new Dao1().selectBystation(qishizhan);
        String qishiluxian=luxian1[0];
        List<Bean1> list = new ArrayList<Bean1>();
        List<Bean1> lister = new ArrayList<Bean1>();
        List<Bean1> listfirst = new ArrayList<Bean1>();
        List<Bean1> listsecond = new ArrayList<Bean1>();
        String[] qianhou=new String[4];//路线的前后
        String[] huanzhan=new String[2];//换站点1,2
        list=new Dao1().selectByNol(qishiluxian);
        int cunt=0;
        int sunt=1;
        for(int i=0;i<list.size();i++) {

            if(qishiluxian.equals(list.get(i).getNolstation())&&qishizhan.equals(list.get(i).getName())||cunt==1) {
                listfirst.add(list.get(i));
                cunt=1;
            }
            if((!qishiluxian.equals(list.get(i).getNolstation()))&&sunt==1) {
                qianhou[0]=list.get(i).getNolstation().substring(0,1);
                System.out.println("前一个路线"+qianhou[0]);
                qianhou[1]=list.get(i).getNolstation().substring(2,3);
                System.out.println("后一个路线"+qianhou[1]);
                huanzhan[0]=list.get(i).getName();
                sunt=0;
                cunt=0;
            }
            if((!qishiluxian.equals(list.get(i).getNolstation()))&&sunt==0) {
                qianhou[2]=list.get(i).getNolstation().substring(0,1);
                System.out.println("前一个路线"+qianhou[2]);
                qianhou[3]=list.get(i).getNolstation().substring(2,3);
                System.out.println("后一个路线"+qianhou[3]);
                huanzhan[1]=list.get(i).getName();
            }
        }
        String[] hui=new String[5];
        hui[0]=huanzhan[0];
        hui[1]=huanzhan[1];
        hui[2]=qianhou[1];
        hui[3]=qianhou[3];
        hui[4]=qianhou[0];//本路线
        for(int i=0;i<5;i++) {
            System.out.print(hui[i]);
        }

        return hui;
    }


    //最终实现查询
    public static List<Bean1> HuanZui(String qishizhan,String endzhan){
        List<Bean1> list = new ArrayList<Bean1>();
        List<Bean1> listz = new ArrayList<Bean1>();
        List<Bean1> listf = new ArrayList<Bean1>();
        List<Bean1> listfi = new ArrayList<Bean1>();
        List<Bean1> listsez = new ArrayList<Bean1>();
        List<Bean1> listsem = new ArrayList<Bean1>();
        String[] ll=Huanzhanlu(qishizhan);//ll[5];
        String[] luxian2=new String[10];
        luxian2=new Dao1().selectBystation(endzhan);
        String ls=luxian2[0];
        System.out.println(ll[4]);//1
        String l2="1";
        System.out.println(ll[4].getClass());
        System.out.println(l2.getClass());
        System.out.println(ls.getClass());
        System.out.println(ll[2]);//4
        System.out.println(ls);//4
        if(ll[2].equals(ls)) {
            System.out.print("换乘站为:"+ll[0]);
            //找起始路线
            listz=new Dao1().selectByNol(ll[4]);
            System.out.print(listz.size());
				/*if(listz.size()>0) {
					System.out.print(ll[4]);
				}*/
            //System.out.print(ll[4]);
            int cunt=0;
            for(int i=0;i<listz.size();i++) {
                if((ll[4].equals(listz.get(i).getNolstation())&&qishizhan.equals(listz.get(i).getName()))||cunt==1) {
                    listfi.add(listz.get(i));
                    cunt=1;
                }
                if(ll[0].equals(listz.get(i).getName())&&cunt==1) {
                    i=listz.size();
                }
            }


            //找第二条线

            listf=new Dao1().selectByNol(ls);
            int cunter=0;
            int wunt=0;
            for(int j=0;j<listf.size();j++) {
                if((ls.equals(listf.get(j).getNolstation())&&endzhan.equals(listf.get(j).getName()))||cunter==1) {
                    listsez.add(listf.get(j));
                    cunter=1;
                }
                //站点开始遍历
                if(ll[0].equals(listf.get(j).getName())||wunt==1) {
                    listsem.add(listf.get(j));
                    wunt=1;
                }
                if(listf.get(j).getName().equals(endzhan)&&wunt==1) {
                    j=listf.size();
                }
                if(listf.get(j).getName().equals(ll[0])&&cunter==1) {
                    j=listf.size();
                }
            }
            //判断哪一个是
            if(listsez.size()>1) {
                Bean1 subject = new Bean1();
                subject.setName(ll[4]+"号线换乘"+ll[2]+"号线");
                listfi.add(subject);
                for(int l=listsez.size()-1;l>=0;l--)
                {
                    listfi.add(listsez.get(l));
                    System.out.println(listsez.get(l).getName());
                }
            }else {
                Bean1 subject = new Bean1();
                subject.setName(ll[4]+"号线换乘"+ll[2]+"号线");
                listfi.add(subject);
                for(int l=0;l<listsem.size();l++)
                {
                    listfi.add(listsem.get(l));
                    System.out.println(listsem.get(l).getName());
                }
            }
        }

        //如果是后一个
        if(ll[3].equals(ls)) {
            System.out.print("换乘站为。"+ll[1]);
            //找起始路线
            listz=new Dao1().selectByNol(ll[4]);
            int cunt=0;
            for(int i=0;i<listz.size();i++) {

                if(ll[4].equals(listz.get(i).getNolstation())&&qishizhan.equals(listz.get(i).getName())||cunt==1) {
                    listfi.add(listz.get(i));
                    cunt=1;
                }
                if(ll[0].equals(listz.get(i).getName())) {
                    i=listz.size();
                }
            }


            //找第二条线
            listf=new Dao1().selectByNol(ls);
            int cunter=0;
            int wunt=0;
            for(int j=0;j<listf.size();j++) {
                if((ls.equals(listf.get(j).getNolstation())&&endzhan.equals(listf.get(j).getName()))||cunter==1) {
                    listsez.add(listf.get(j));
                    cunter=1;
                }

                //站点开始遍历
                if((ll[1].equals(listf.get(j).getName())||wunt==1)&&cunter==0) {
                    listsem.add(listf.get(j));
                    wunt=1;
                }
                if(listf.get(j).getName().equals(endzhan)&&wunt==1) {
                    j=listf.size();
                }

                if(listf.get(j).getName().equals(ll[1])&&cunter==1) {
                    j=listf.size();
                    cunt=0;
                }
            }
            //判断哪一个是
            if(listsez.size()>1) {
                Bean1 subject = new Bean1();
                subject.setName(ll[4]+"号线换乘"+ll[3]+"号线");
                listfi.add(subject);
                for(int l=listsez.size()-1;l>=0;l--)
                {
                    listfi.add(listsez.get(l));
                    System.out.println(listsez.get(l).getName());
                }
            }else {
                Bean1 subject = new Bean1();
                subject.setName(ll[4]+"号线换乘"+ll[3]+"号线");
                listfi.add(subject);
                for(int l=0;l<listsem.size();l++)
                {
                    listfi.add(listsem.get(l));
                    System.out.println(listsem.get(l).getName());
                }
            }
        }

        for(int k=0;k<listfi.size();k++) {
            System.out.println(listfi.get(k).getName());
        }

        return listfi;
    }


    public static void main(String[] args) {
        String qishizhan="苹果园";
        String endzhan="西苑";
        //list=Huanzhan(qishizhan,endzhan);

    }
}
