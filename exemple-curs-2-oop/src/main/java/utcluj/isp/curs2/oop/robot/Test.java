/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utcluj.isp.curs2.oop.robot;

/**
 *
 * @author mihai
 */
public class Test {
   public static void main(String[] args) {
        Robot1 robot = new Robot1();
        System.out.println("Robot name: " + robot);
        robot.name = 1234;
        robot.position = 0;
        System.out.println("Robot name: " + robot.name);
        System.out.println("Robot position: " + robot.position);

        Robot1 r2 = new Robot1();
        Robot1 r3 = new Robot1();
        Robot1 r4 = new Robot1();

        r2.position = 10;
        r4.position = 50;
       System.out.println(".......................");
       System.out.println(r2);
       System.out.println(r3);
       System.out.println(r4);
       System.out.println(".......................");
       r2 = r3;
       System.out.println(r2);
       System.out.println(r3);
       System.out.println(r2.position);

       String s1 = "aaaaa";
       String s2 = new String("bbbbb");

       Robot1 r5 = null;

   }
}
