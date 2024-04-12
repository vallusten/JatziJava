package com.example.jatzi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.HashSet;
import java.util.HashMap;



public class pelintoiminta {
        ArrayList<Integer> tilanne1 = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0));
    ArrayList<Integer> tilanne2 = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
    ArrayList<Integer> tilanne3 = new ArrayList<Integer>(Arrays.asList(0, 0));
    ArrayList<Integer> tilanne4 = new ArrayList<Integer>(Arrays.asList(0));
        Random randomNum = new Random();
        int nopanluku = randomNum.nextInt(6);
        int xpainettu = 0;
        //
        //
        public static boolean vertailu(int[] x) {
            HashSet<Integer> set = new HashSet<>();
            for (int num : x) {
                if (!set.add(num)) {
                    return true; // Duplicate found
                }
            }
            return false; // No duplicates found
        }

    public static boolean vertailu2(int[] x) {
        HashMap<Integer, Integer> numerot = new HashMap<>();
        for (int n : x) {
            numerot.put(n, numerot.getOrDefault(n, 0) + 1);
        }

        if (numerot.size() == 2) {
            for (int count : numerot.values()) {
                if (count >= 4) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 5};
        int[] arr2 = {1, 2, 2, 3, 3, 3};
        int[] arr3 = {1, 2, 2, 3, 3};                                                                                    // vertailu 1 ja vertailu 2 kirjoitettu pythonilla ja käännetty chatgpt:n avulla javaan

    }

        //

    /**
     * Nopan heitto
     * @return Palauttaa nopan silmäluvut ja tuloksen
     */
    public ArrayList[] heitto() {
            xpainettu++;
            ArrayList<Integer> nopat = new ArrayList<Integer>();

            for (int i = 0; i < 5; i++) {
                Random randomNum = new Random();
                int silmaluku = randomNum.nextInt(6) + 1;
                nopat.add(silmaluku);

            }
            int bonus = 0;
            int alkupuoli = 0;
            if (xpainettu >= 7) {
                for (int i = 0; i < tilanne1.size(); i++) {
                    alkupuoli += tilanne1.get(i);
                    if (alkupuoli >= 60) {
                        bonus = 25;
                    }
                }
            }
            int alkupisteet = bonus + alkupuoli;
            tilanne3.set(1, alkupisteet);
            tilanne3.set(0, bonus);

            if (xpainettu <= 6) {
                int counter = 0;
                Boolean loop = true;
                for (int i = 6; i >= 1; i--) {
                    for (int j = 6; j >= 0; j--) {
                        for (int noppa : nopat) {
                            System.out.println(noppa);
                            if (noppa == j) {
                                counter += 1;
                            }
                        }
                        if (counter >= i && tilanne1.get(j - 1) == 0) {
                            tilanne1.set(j - 1, counter * j);
                            System.out.println(i + "-" + j + "-" + counter);
                            counter = 0;
                            loop = false;
                            break;
                        } else {
                            counter = 0;
                        }
                    }
                    if (!loop) break;
                }
                System.out.println(Arrays.toString(tilanne1.toArray()));
            }
            ////
            else {
                int kierros = 5;
                for (int k = 6; k >= 1; k--) {
                    kierros -= 1;
                    int counter = 0;
                    Boolean loop = true;
                    int[] nopatArray = new int[5];
                    int index = 0;
                    for(Integer noppa : nopat) {
                        nopatArray[index] = noppa;
                        index++;
                    }
                    if (vertailu(nopatArray)){
                        if (vertailu2(nopatArray)) {
                            var tk = 0;
                            for (int n : nopat) {
                                tk += n;
                                tilanne2.set(2, tk);
                                break;
                            }
                        } else {
                            for (int i = 6; i >= 1; i--) {
                                counter = 0;
                                for (int j = 6; j >= 0; j--) {
                                    for (int noppa : nopat) {
                                        if (noppa == j) {
                                            counter += 1;
                                            continue;
                                        } else {
                                            continue;
                                        }
                                    }
                                    if (counter >= i) {
                                        if (i == 5) {
                                            tilanne2.set(5, 50);
                                            loop = false;
                                            break;
                                        } else if (i == 4) {
                                            tilanne2.set(1, (j * 4));
                                            loop = false;
                                            break;
                                        } else if (i == 3) {
                                            tilanne2.set(0, (j * 3));
                                            loop = false;
                                            break;
                                        } else {
                                            int sattumasumma = 0;
                                            for ( Integer n:nopat) {
                                                sattumasumma += n;
                                                if (tilanne2.get(6) == 0) {
                                                    tilanne2.set(6, sattumasumma);
                                                    loop = false;
                                                    break;
                                                } else {
                                                    continue;
                                                }
                                            }
                                        }

                                    } else {
                                        continue;
                                    }
                                } if (!loop) break;
                            }
                        }
                    } else {
                        int suora = 0;
                        for (Integer n: nopat) {
                            suora += n;
                            if (suora == 20) {
                                tilanne2.set(4, 20);
                            } else if (suora == 15) {
                                tilanne2.set(3, 15);
                            }
                            //
                        }
                    }
                }
                if  (xpainettu >= 13){
                    int yhteispisteet = 0;
                    for (Integer piste : tilanne2){
                        yhteispisteet += piste;
                    }
                    for (Integer homma : tilanne3){
                        yhteispisteet += homma;
                    }
                    tilanne4.set(0, yhteispisteet);
                }
                System.out.println(Arrays.toString(tilanne2.toArray()));
            }
            ArrayList[] palautus = {nopat, tilanne1, tilanne2, tilanne3, tilanne4};
            return palautus;
        }


        public pelintoiminta(){

        }


}