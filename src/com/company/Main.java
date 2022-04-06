package com.company;

import java.util.Random;

public class Main {
    public static String bossDefenceType;
    public static int bossDamage = 100;
    public static int bossHealth = 2000;
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static int[] heroesDamage = {25, 20, 15, 0, 5, 10, 5, 5};
    public static int[] heroesHealth = {340, 270, 250, 300, 400, 200, 250, 200};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        bossDefenceType = chooseDefence();
        Thor();
        bossHits();
        heroesHit();
        medic();
        Golem();
        Lucky();
        Berserk();
        printStatistics();
    }

    public static void medic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                if (heroesHealth[i] == heroesHealth[3]) {
                    continue;
                }
                heroesHealth[i] += 50;
                System.out.println(heroesAttackType[i] + " medic вылечил");
                break;
            }
        }
    }

    public static void Golem() {
        int dmg = bossDamage / 5;
        int gg = 0;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 4) {
                continue;
            } else if (heroesHealth[i] > 0) {
                gg++;
                heroesHealth[i] += dmg;
            }
            heroesHealth[4] -= dmg * gg;
            System.out.println(" Golem took damage " + dmg * gg);
            break;
        }
        if (heroesHealth[4] <= 0) {
            heroesHealth[4] = 0;
        }
    }

    public static void Lucky() {
        Random random = new Random();
        boolean aqd = random.nextBoolean();
        if (heroesHealth[5] > 0 && aqd) {
            heroesHealth[5] += bossDamage - bossDamage / 5 + 10;
        }
        System.out.println("");

    }

    {
    }

    public static void Thor() {
        Random random = new Random();
        boolean oglushka = random.nextBoolean();
        if (heroesHealth[7] > 0 && oglushka) {
            bossDamage = 0;
            System.out.println("Thor оглушил");
        } else {
            bossDamage = 50;
        }
    }

    {
    }

    public static void Berserk() {
        for (int i = 0; i < heroesHealth.length ; i++) {
            if (heroesHealth[6] > 0) {
                heroesHealth[6] += bossDamage / 5 * 2;
                bossHealth -= bossDamage / 5 * 2;
                break;
            }else {
                bossDamage = 50;
            }
        }
    }

    {
    }


    public static String chooseDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        System.out.println("Boss chose " + heroesAttackType[randomIndex]);
        return heroesAttackType[randomIndex];
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] + " x "
                            + coeff + " = " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/

        int totalHealth = 0;
        for (int health : heroesHealth) {
            totalHealth += health; // totalHealth = totalHealth + health;
        }
        if (totalHealth <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void printStatistics() {
        System.out.println(roundNumber + " ROUND _________________");

        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " +
                    heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
    }
}