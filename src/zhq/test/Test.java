package zhq.test;


import zhq.algorithm.PopEA;
import zhq.parse.Parse;
import zhq.preparework.Person;
import zhq.preparework.PrepareWork;

import java.util.Arrays;
import java.util.List;

public class Test {


    public static void main(String[] args) {


        //��ȡ���������ݼ�
        Parse p = new Parse("src/input.txt");
        p.parse();
        PrepareWork pWork = p.getPrepareWork();
        PopEA popEA = new PopEA(pWork, 64, 7, 0.4);
        //��ʼ����Ⱥ
        List<Person> pop = popEA.initPop();
        List<Person> parents;
        List<Person> child;
        for (int i = 0; i < 1000; i++) {
            System.out.println("��"+i+"�ε�����");
            //���ö����ƽ��������ƴ���Ⱥ��ɸѡ���ڲ����Ӵ��ĸ���
            //64->32->16
            parents = popEA.binTournament(pop, 2);
//            for (Person person : parents) {
//                System.out.println("binTournament  " + person.getFitness());
//            }
            //�Ը������ý�����������Ӵ�
            child = popEA.crossover(parents);
            //���Ӵ����б������
            popEA.mutation(child);
            //���þ�Ӣ���Դ��Ӵ�����Ⱥ��ѡ����һ����Ⱥ
            pop = popEA.select(pop, child);
            System.out.println("best_score:"+pop.get(0).getFitness());
        }
//        System.out.println(pop.get(0));
        double[][] res = pop.get(0).getMatrix();
        for (int i = 0; i < res.length; i++) {
            System.out.println(Arrays.toString(res[i]));
        }

        popEA.dispatch(pop.get(0), "G:/projects/javalearn/experimentResult/res.txt");

    }
}
