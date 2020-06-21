package zhq.test;


import zhq.algorithm.PopEA;
import zhq.parse.Parse;
import zhq.preparework.Person;
import zhq.preparework.PrepareWork;

import java.util.Arrays;
import java.util.List;

public class Test {


    public static void main(String[] args) {


        //读取并解析数据集
        Parse p = new Parse("src/input.txt");
        p.parse();
        PrepareWork pWork = p.getPrepareWork();
        PopEA popEA = new PopEA(pWork, 64, 7, 0.4);
        //初始化种群
        List<Person> pop = popEA.initPop();
        List<Person> parents;
        List<Person> child;
        for (int i = 0; i < 1000; i++) {
            System.out.println("第"+i+"次迭代：");
            //采用二进制锦标赛机制从种群中筛选用于产生子代的父代
            //64->32->16
            parents = popEA.binTournament(pop, 2);
//            for (Person person : parents) {
//                System.out.println("binTournament  " + person.getFitness());
//            }
            //对父代采用交叉操作产生子代
            child = popEA.crossover(parents);
            //对子代进行变异操作
            popEA.mutation(child);
            //采用精英策略从子代和种群中选择下一代种群
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
