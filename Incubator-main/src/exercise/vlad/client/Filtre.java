package exercise.vlad.client;

import java.awt.Graphics;


interface Filtre{
    public void receiveData(Object s, Graphics g,int width, int height);  // primirea datelor
    public void setText();                                                // afisarea in Canvas a textului
    public void compareWord(String s);                                    //  verificare daca este indeplinita conditia de cuvant
    public int findHeight();                                              // pentru a afla inaltimea textului
}