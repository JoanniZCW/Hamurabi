package hammurabi.src.main.java;
import java.util.Random;
import java.util.Scanner;

/*
Finished:
Create pom.xml
Create hammurabi class and copy the skeleton that Kris provided
Info we need: 100 people, 2800 bushels of grain in storage, 1000 acres of land, land value 19;
Create a system.out.println of summary of the game
PlayGame method: int askHowManyAcresToBuy(int price, int bushels) -> return #
 Sanity check print output message
 PlayGame method: int askHowManyAcresToSell(int acresOwned) -> return #
 Sanity check print output message
 PlayGame method: int askHowMuchGrainToFeedPeople(int bushels) -> return #
 Sanity check print output message
 PlayGame method: int askHowManyAcresToPlant(int acresOwned, int population, int bushels) -> return #
 Sanity check print output message
CALCULATE in methods
- int plagueDeaths(int population)
  - 15% chance of plague, if plague 50% die, return # of deaths, possibility 0
   - If there is a plague, and how many people die from it. -> boolean plague, int deathsFromPlague
- int starvationDeaths(int population, int bushelsFedToPeople)
  - each person = 20 bushels to survive, return number of deaths from starvation, possibility 0
- boolean uprising(int population, int howManyPeopleStarved)
  - true if >= 45% people starve, thrown out of office and end game
     - How many people starved. -> int numPeopleStarved
- int immigrants(int population, int acresOwned, int grainInStorage)
  - (boolean) if starvation, no immigrants; if no starvation, immigrant = (20 * _number of acres you have_ + _amount of grain you have in storage_) / (100 * _population_) + 1
     - How many people came to the city. -> int immigrants
- int harvest(int acres, int bushelsUsedAsSeed)
  - random int 1-6 inclusive, bushelsPerAcre * numberOfAcres, return numberOfBushels harvested
- int grainEatenByRats(int bushels)
  - 40% rat infestation if true rats eat 10-30% of bushels, return bushelsEatenByRats, possibly 0
     - How good the harvest is. -> int bushelsPerAcre
     - If you have a problem with rats, and how much grain they eat. -> boolean rats, int bushelsEatenByRats
- int newCostOfLand()
  - is random number 17-23 bushelsPerAcre, return new cost
      - How much land costs (for deciding what to do next). -> int costPerAcre
- Method finalSummary for gameEnding; how good of a job they did, total number of people starved, how many acres per person


Need to complete:

- REMEMBER playGame = 10 years

*/

public class Hammurabi {
	Random rand = new Random();
	Scanner scanner = new Scanner(System.in);

	int currentAcres = 1000;
	int bushelsInStorage = 2800;
	int peasants = 100;
	int starvationDeaths = 0;
	boolean over = true;
	//boolean plague = false;
	int plagueDeaths = 0;
	int immigrants = 5;
	int bushelPerAcreOfHarvest = 0;
	boolean rats = false;
	int grainEatenByRats = 0;
	int costPerAcre = 0;
	int year = 0;
	boolean uprising = true;

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
//		askHowManyAcresToBuy(17,2800);
//		askHowManyAcresToSell(1000);
//		askHowMuchGrainToFeedPeople(2800);
//		askHowManyAcresToPlant(1000,100, 2800);
//		for (int i = 1; i <= 10 ; i++) {
//			if (over() == true) {
//				break;
//			} else if (uprising(peasants, starvationDeaths) == true) {
//				break;
//			}
//		}
	}

	//other methods go here
	public int askHowManyAcresToBuy(int price, int bushel) {
		System.out.println("How many acres do you wish to buy?");
		Scanner scanner = new Scanner(System.in);
		int acresToBuy = scanner.nextInt();
		if ((acresToBuy * price) > bushel) {
			System.out.println("O Great Joanni, surely you suck! We have only " + bushel + " bushels left!");
		} else {
		}
		return acresToBuy;
	}

	public int askHowManyAcresToSell(int acresOwned) {
		System.out.println("How many acres do you wish to sell?");
		Scanner scanner = new Scanner(System.in);
		int acresToSell = scanner.nextInt();
		if ((acresOwned < acresToSell)) {
			System.out.println("O Great Joanni, surely you suck! We have only " + acresToSell + " acres!");
		}
		return acresToSell;
	}

	public int askHowMuchGrainToFeedPeople(int bushel) {
		System.out.println("How many bushels do you want to feed the peasants?");
		Scanner scanner = new Scanner(System.in);
		int grainToFeedPeople = scanner.nextInt();
		if (bushel < grainToFeedPeople) {
			System.out.println("O Great Joanni, surely you suck! We have only " + bushel + " people!");
		}
		return grainToFeedPeople;
	}

	public int askHowManyAcresToPlant(int acresOwned, int population, int bushel) {
		System.out.println("How many acres do you wish to plant with seed?");
		Scanner scanner = new Scanner(System.in);
		int acresToPlant = scanner.nextInt();
		if (acresToPlant > acresOwned) {
			System.out.println("O Great Joanni, surely you suck! We have only " + acresOwned + " acres!");
		}
		if (acresToPlant > (population * 10)) {
			System.out.println("O Great Joanni, surely you suck! We have only " + population + " peasants!");
		}
		if (acresToPlant > bushel) {
			System.out.println("O Great Joanni, surely you suck! We have only " + bushel + " bushels!");
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
		if (population * .45 <= numberOfPeopleStarved) {
			return true;// need game to be over if true
		} else {
			return false;
		}
	}

	public int immigrants(int population, int acresOwned, int grainInStorage) {
		if (starvationDeaths == 0) {
			return (20 * acresOwned + grainInStorage) / (100 * population) + 1;
		} else {
			return 0;
		}
	}

	public int harvest(int acres) {
		int roll = rand.nextInt(6) + 1;
		return roll * acres;
	}

	public boolean rats() {
		int roll = rand.nextInt(100);
		if (roll < 40) {
			return true;
		}
		return false;
	}

	public int grainEatenByRats(int bushels) {
		int percentage = rand.nextInt(21) + 10;
		if (rats() == false) {
			return 0;
		}
		return percentage;
	}

	public int newCostOfLand() {
		return (rand.nextInt(7) + 17);
	}

	public int howManyAcresPerPerson() {
		return currentAcres / peasants;
	}
	public void printFinalSummary() {
		System.out.println("This is your final report: ");
		System.out.println("In your 10 year term of office");
		System.out.println("You have " + peasants + "peasants");
		System.out.println("You only starved " + starvationDeaths + " people");
		System.out.println("You now have " + howManyAcresPerPerson() + " acres per person");
	}
	public void printYearlySummary() {
		System.out.println("I beg to report to you: ");
		System.out.println("In year " + year + ", " + starvationDeaths + " people starved");
		System.out.println(immigrants + "peasants came to the city");
		System.out.println("The city population is now " + peasants);
		System.out.println("The city now owns " + currentAcres + " acres");
		System.out.println("You harvested " + bushelPerAcreOfHarvest + " bushels per acre");
		System.out.println("Rats ate  " + grainEatenByRats + " bushels");
		System.out.println("You now have " + bushelsInStorage + " bushels in store");
		System.out.println("Land is trading at " + costPerAcre + " bushels per acre");
	}
	public boolean over() {
		if (year == 10) {
			return over;
		} else {
			return false;
		}
	}
}