package hammurabi.src.main.java;

import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("O great lord, Hammurabi!");
        System.out.println("Let us see how smart or stupid of a ruler you are.");
        System.out.println("Under previous ruler no people died, and 5 new people entered the kingdom.");
        System.out.println("You now have 100 peasants to rule and control.");
        System.out.println("You have been blessed with 3000 bushels and 1000 acres getting 3 bushels from every acre.");
        System.out.println("Plague ridden rats have eaten and destroyed 200 bushels of food, leaving 2800 bushels in storage.");
        System.out.println("Your land is currently trading for 19 bushels per acre.");
        System.out.println("Rule well and do not fork it up or else...");
        new Hammurabi().playGame();
    }

    void playGame() {
        int currentAcres = 1000;
        int bushelsInStorage = 2800;
        int peasants = 100;
        int starvationDeaths = 0;
        int plagueDeaths = 0;
        int immigrants = 5;
        int bushelPerAcreOfHarvest = 0;
//        boolean rats = false;
        int grainEatenByRats = 200;
        int costPerAcre = 19;
        int year = 1;
        while (year <= 10) {
            int acresBought = askHowManyAcresToBuy(costPerAcre, bushelsInStorage);
            currentAcres = currentAcres + acresBought;
            bushelsInStorage = bushelsInStorage - (acresBought * costPerAcre);
            System.out.println("Bushels remaining in storage: " + bushelsInStorage);
//            System.out.println("this updates current acres by bought");

            int acresSold = askHowManyAcresToSell(currentAcres);
            currentAcres = currentAcres - acresSold;
            bushelsInStorage = bushelsInStorage + (acresSold * costPerAcre);
            System.out.println("Bushels remaining in storage: " + bushelsInStorage);
//            System.out.println("this updates current acres by sold");

            int foodToProvidePeasants = askHowMuchGrainToFeedPeople(bushelsInStorage);
            bushelsInStorage = bushelsInStorage - foodToProvidePeasants;
            System.out.println("Bushels remaining in storage: " + bushelsInStorage);
//            System.out.println("this updates current bushels in storage after food given to pop");

            int howMuchToPlant = askHowManyAcresToPlant(currentAcres, peasants, bushelsInStorage);
            bushelsInStorage = bushelsInStorage - (howMuchToPlant * 2);
//            System.out.println("this updates bushel storage after hwo much is planted");

            starvationDeaths = starvationDeaths(peasants, foodToProvidePeasants);
            peasants = peasants - starvationDeaths;
//            System.out.println("this updates peasant pop by starvation deaths");

            bushelPerAcreOfHarvest = harvest();

            int totalHarvest = totalHarvest(bushelPerAcreOfHarvest, howMuchToPlant);
            bushelsInStorage = bushelsInStorage + totalHarvest;
//            System.out.println("this updates bushels storage by total harvested");

            costPerAcre = newCostOfLand();
//            System.out.println("renews land cost for next yr");

            plagueDeaths = plagueDeaths(peasants);
            peasants = peasants - plagueDeaths;
//            System.out.println("updates peasant pop by plague deaths");

            grainEatenByRats = grainEatenByRats(bushelsInStorage);
            bushelsInStorage = bushelsInStorage - grainEatenByRats;
//            System.out.println("updates bushels storage by grain eaten by rats");

            immigrants = immigrants(peasants, currentAcres, bushelsInStorage, starvationDeaths);
            peasants = peasants + immigrants;
//            System.out.println("this updates peasant pop by new immigration");

//            if (uprising(peasants,starvationDeaths)) {
//                System.out.println("There has been an uprising. You've killed over 45% of people");
//                System.exit(0);
//            }
//            System.out.println("this ends game if uprising from starving");
            if (year == 10) {
                System.exit(0);
            }
//            System.out.println("this ends game once reached 10 yrs of rule");

            printYearlySummary(year, starvationDeaths, plagueDeaths, immigrants, peasants, currentAcres, bushelPerAcreOfHarvest,
                    grainEatenByRats, bushelsInStorage, costPerAcre);
//            System.out.println("printed yr report");
            year++;
//            System.out.println("increased yr by 1");
        }
    }

    //other methods go here
    public int askHowManyAcresToBuy(int price, int bushel) {
        System.out.println("How many acres do you wish to buy?");
        int acresToBuy = scanner.nextInt();
        if ((acresToBuy * price) > bushel) {
            System.out.println("O Great Hammurabi, surely you suck! We have only " + bushel + " bushels left!");
            return 0;
        }
        return acresToBuy;
    }

    public int askHowManyAcresToSell(int acresOwned) {
        System.out.println("How many acres do you wish to sell?");
        int acresToSell = scanner.nextInt();
        if (acresOwned < acresToSell) {
            System.out.println("O Great Hammurabi, surely you suck! We have only " + acresToSell + " acres!");
            return 0;
        }
        return acresToSell;
    }

    public int askHowMuchGrainToFeedPeople(int bushel) {
        System.out.println("How many bushels do you want to feed the peasants?");
        int grainToFeedPeople = scanner.nextInt();
        if (bushel < grainToFeedPeople) {
            System.out.println("O Great Hammurabi, surely you suck! We have only " + bushel + " people!");
            return 0;
        }
        return grainToFeedPeople;
    }

    public int askHowManyAcresToPlant(int acresOwned, int population, int bushel) {
        System.out.println("How many acres do you wish to plant with seed?");
        int acresToPlant = scanner.nextInt();
        if (acresToPlant > acresOwned) {
            System.out.println("O Great Hammurabi, surely you suck! We have only " + acresOwned + " acres!");
            return 0;
        }
        if (acresToPlant > (population * 10)) {
            System.out.println("O Great Hammurabi, surely you suck! We have only " + population + " peasants!");
            return 0;
        }
        if (acresToPlant > bushel) {
            System.out.println("O Great Hammurabi, surely you suck! We have only " + bushel + " bushels!");
            return 0;
        }
        return acresToPlant;
    }

    public boolean plague() {
        int roll = rand.nextInt(100);
        if (roll < 15) {
            return true;
        }
        return false;
    }

    public int plagueDeaths(int population) {
        if (plague() == true) {
            population = population / 2;
        } else {
            population = 0;
        }
        return population;
    }

    public int starvationDeaths(int population, int bushelsFedToPeople) {
        int numberOfDeathsFromStarvation = 0;
        if (bushelsFedToPeople >= population * 20) {
            return numberOfDeathsFromStarvation;
        } else {
            numberOfDeathsFromStarvation = population - (int) (Math.floor(bushelsFedToPeople / 20));
        }
        return numberOfDeathsFromStarvation;
    }

    public boolean uprising(int population, int numberOfPeopleStarved) {
        if ((population * 100) / 45 <= numberOfPeopleStarved) {
            return true;// need game to be over if true
        } else {
            return false;
        }
    }

    public int immigrants(int population, int acresOwned, int grainInStorage, int starvationDeaths) {
        if (starvationDeaths == 0) {
            return (20 * acresOwned + grainInStorage) / (100 * population) + 1;
        } else {
            return 0;
        }
    }

    public int harvest() {
        int roll = rand.nextInt(6) + 1;
        return roll;
    }

    public int totalHarvest(int harvestPerAcre, int acrePlantedWSeed) {
        int totHarv = harvestPerAcre * acrePlantedWSeed;
        return totHarv;
    }

//    public boolean rats() {
//        int roll = rand.nextInt(100);
//        if (roll < 40) {
//            return true;
//        }
//        return false;
//    }

    public int grainEatenByRats(int bushels) {
        int percentage = rand.nextInt(21) + 10;
        if (rand.nextInt(100) < 40) {
            return (percentage * bushels) / 100;
        }
        return 0;
    }

    public int newCostOfLand() {
        return (rand.nextInt(7) + 17);
    }

//    public int acresBought(int bushelsInStorage, int acresToBuy, int costPerAcre) {
//        int countAcresBought = 0;
//        if (bushelsInStorage >= acresToBuy * costPerAcre) {
//            countAcresBought = askHowManyAcresToBuy(costPerAcre, bushelsInStorage);
//        }
//        return countAcresBought;
//    }


//	public void printFinalSummary() {
//		System.out.println("This is your final report: ");
//		System.out.println("In your 10 year term of office");
//		System.out.println("You have " + peasants + " peasants");
//		System.out.println("You only starved " + starvationDeaths + " people");
////		System.out.println("You now have " + howManyAcresPerPerson() + " acres per person");
//	}

    public void printYearlySummary(int year, int starvationDeaths, int plagueDeaths, int immigrants, int peasants,
                                   int currentAcres, int bushelPerAcreOfHarvest, int grainEatenByRats, int bushelsInStorage, int costPerAcre) {
        System.out.println("I beg to report to you: ");
        System.out.println("In year " + year + ", " + starvationDeaths + " people starved");
        System.out.println(plagueDeaths + " deaths from the plague");
        System.out.println(immigrants + " peasants came to the city");
        System.out.println("The city population is now " + peasants);
        System.out.println("The city now owns " + currentAcres + " acres");
        System.out.println("You harvested " + bushelPerAcreOfHarvest + " bushels per acre");
        System.out.println("Rats ate " + grainEatenByRats + " bushels");
        System.out.println("You now have " + bushelsInStorage + " bushels in store");
        System.out.println("Land is trading at " + costPerAcre + " bushels per acre");
    }
}