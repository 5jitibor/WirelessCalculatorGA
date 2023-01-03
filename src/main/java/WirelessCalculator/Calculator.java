package WirelessCalculator;

import java.util.HashMap;

import static java.lang.Math.log10;

public class Calculator {

    public static  HashMap<TypeScenarioEnum,Double> calculatePropagationLoss(double f, double hb, double hm, double d){
        double af = commonCalculateAF(f,hb);
        double b = commonCalculateB(hb);
        double common = af + (b * Math.log10(d));

        HashMap<TypeScenarioEnum,Double> results = commonCalculateC(f,hm);

        for(TypeScenarioEnum type : TypeScenarioEnum.values()){
            results.put(type, common - results.get(type));
        }

        if (f > 1500) {
            results.put(TypeScenarioEnum.BIGCITY, results.get(TypeScenarioEnum.BIGCITY) + 3);
        }


        return results;
    }

    public static HashMap<TypeScenarioEnum,Double> calculateCoverage(double f, double hb, double hm, double ptx, double gtx, double grx, double srx ){
        double af = commonCalculateAF(f,hb);
        double b = commonCalculateB(hb);
        double l = ptx - srx + gtx + grx;

        HashMap<TypeScenarioEnum,Double> results = commonCalculateC(f, hm);

        if (f > 1500) {
            results.put(TypeScenarioEnum.BIGCITY, results.get(TypeScenarioEnum.BIGCITY) - 3);
        }

        for(TypeScenarioEnum type : TypeScenarioEnum.values()){
            double finalResult = Math.pow(10,(l - af + results.get(type))/b);
            if (finalResult >= 1 && finalResult <= 20) {
                results.put(type, finalResult);
            } else {
                results.put(type, -1.0);
            }
        }

        return results;
    }


    public static double commonCalculateB(double hb){
        return 44.9 - (6.55 * Math.log10(hb));
    }


    public static double commonCalculateAF(double f, double hb){
        double af = -(13.82 * Math.log10(hb));
        if(f<=1500){
            af+= 69.55 + (26.16 * Math.log10(f));
        }
        else{
            af += 46.3 + (33.9 * Math.log10(f));
        }

        return af;

    }

    public static HashMap<TypeScenarioEnum,Double> commonCalculateC(double f, double hm){
        HashMap<TypeScenarioEnum, Double> results = new HashMap<>();

        if(f<300){
            results.put(TypeScenarioEnum.BIGCITY, ((8.29 * Math.pow(log10(1.54 * hm), 2)) - 1.1));
        }
        else{
            results.put(TypeScenarioEnum.BIGCITY, ((3.2 * Math.pow(log10(11.75 * hm), 2)) - 4.97));
            /*if(f>1500){
                results.put(TypeScenarioEnum.BIGCITY, results.get(TypeScenarioEnum.BIGCITY) - 3);
            }*/
        }

        results.put(TypeScenarioEnum.MEDIUMSMALLCITY, ((((1.1 * Math.log10(f)) - 0.7) * hm) - (1.56 * Math.log10(f)) + 0.8));

        results.put(TypeScenarioEnum.SUBURBANCITY, (2 * Math.pow(log10(f/28), 2) + 5.4));

        results.put(TypeScenarioEnum.RURAL, (4.78 * Math.pow(log10(f), 2) - (18.33 *(log10(f))) + 40.94));

        return  results;
    }


}
