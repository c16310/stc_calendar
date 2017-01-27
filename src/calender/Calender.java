/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;
//コマンドライン引数で与えられた月のカレンダーを表示
import java.util.GregorianCalendar;
import static java.util.GregorianCalendar.*;
/**
 *
 * @author c16310
 */
public class Calender {
    //各月の日数
    static int [] mday ={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
    
    //year年month月day日の曜日を求める
    static int dayOfWeek(int year, int month, int day){
        if (month == 1 || month == 2) {
            year--;
            month += 12;
        }
        return (year + year/4 - year/100 + year/400 + (13*month + 8)/5 + day)%7;
    }
    
    //year年は閏年か求める(0なら平年　1なら閏年)
    static boolean isLeap(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 ==0;
    }
    
    //year年month月の日数を求める (28~31)
    static int monthDay(int year, int month) {
        if (month-- != 2)   //monthに2月が出ない時
               return mday[month];
        return mday[month] + (isLeap(year) ? 1 : 0);   //monthに2月がある時
    }
    
    //y年m月のカレンダーの表示
    static void putCalendar(int year, int month) {
        int wd = dayOfWeek(year, month, 1);//year年month月1日の曜日
        int mdays = monthDay(year, month);//year年month月の日数
        
        System.out.println("日 月 火 水 木 金 土");
        System.out.println("----------------------");
        
        for (int i = 0; i < wd; i++) 
        System.out.print("  ");//1日より左側のスペースを表示
        
        for (int i = 1; i <= mdays; i++) {
            System.out.printf("%3d", i);
            if (++wd % 7 == 0)//土曜日を表示したら
                System.out.println();//改行する
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int year = 1, month = 1;
        
        if (args.length == 0) {
            GregorianCalendar today = new GregorianCalendar();//現在の日付
            year = today.get(YEAR);         //年
            month = today.get(MONTH) + 1;   //月
        } else {
            if (args.length >= 1) {
                year = Integer.parseInt(args[0]);//args[0]の解析
                if (year < 0) {
                    System.out.println("年の指定が不正です");
                    return;
                }
            }
            if (args.length >= 2) {              //args[1]の解析
                month = Integer.parseInt(args[1]);
                if (month < 1 || month > 12) {
                    System.out.println("月の指定が不正です");
                    return;
                }
            }
        }
        if (args.length == 0 || args.length >= 2) {
            System.out.printf("%d年%d月のカレンダー\n", year, month);
            putCalendar(year, month);           //year年month月のカレンダーを表示
        } else {
            System.out.printf("%d年のカレンダー\n", year);
            for (month = 1; month <= 12; month++) {
                System.out.printf("%d月\n", month);
                putCalendar(year, month);       //year年month月のカレンダーを表示
                System.out.println();
            }
        }
    }
}
