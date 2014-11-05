package ua.khture.ilinskyi.mmo.lab1;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class Algoritm_Deikstri {

      public static void main(String[] args) {
       
       int[][] matr = new int[6][6]; 
       int[][] Result = new int[6][6];
       int CurHeight = 1; // Текущая вершина для проверки
       
       for(int j = 0; j < matr.length; j++){
           for(int i = 0; i < matr.length; i++){
               matr[i][j]=100;
               if(i==j)matr[j][i]=0;
              // System.out.print(matr[i][j]+ " ");
           }
          // System.out.println();
       }
       
       // Записываем значения в матрицу из графа
       matr[0][1] = 7; matr[0][2] = 9; matr[0][5] = 14;
       matr[1][0] = 7; matr[1][2] = 10; matr[1][5] = 15;
       matr[2][0] = 9; matr[2][1] = 10; matr[2][3] = 11; matr[2][5] = 2;
       matr[3][1] = 15; matr[3][2] = 11; matr[3][4] = 6;
       matr[4][3] = 6; matr[4][5] = 9;
       matr[5][0] = 14; matr[5][2] = 2; matr[5][4] = 9;
       
       
        // Вывод матрицы
        for (int j = 0; j < matr.length; j++) { // строка
            for (int i = 0; i < matr.length; i++) { // столбец
                System.out.print(matr[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("");

        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 6; i++) {
                Result[j][i] = 100;
            }
        }
        Result[0][0] = 0;


        for (int i = CurHeight - 1; i < 6; i++) {
            if (matr[CurHeight - 1][i] < Result[i][CurHeight - 1]) {
                //System.out.print(Result[i][0] + "i" + matr[CurHeight-1][i] + " ");
                Result[i][CurHeight - 1] = matr[CurHeight - 1][i];
            }
        }

//        for (int j = 0; j < 6; j++) {
//            for (int i = 0; i < 6; i++) {
//                Result[j][i] = Result[j][0];
//            }
//        }
        
        CurHeight++;

        while (CurHeight < 7) {

            for (int i = CurHeight + 1; i < Result.length; i++) {
//                if (matr[CurHeight - 1][i] < Result[i][CurHeight - 1]) {
//                    //System.out.print(Result[i][0] + "i" + matr[CurHeight-1][i] + " ");
//                    Result[i][CurHeight - 1] = matr[CurHeight - 1][i];
//                    if (CurHeight - 2 >= 0) {
//                        Result[i][CurHeight - 1] = matr[CurHeight - 1][i] + Result[CurHeight - 1][CurHeight - 2];
//
//                        if (Result[CurHeight - 1][CurHeight - 2] > Result[CurHeight - 1][0]) {
//                            Result[CurHeight - 1][CurHeight - 2] = Result[CurHeight - 1][0];
//                        }
//                    }
//
//                }
                //System.out.print(matr[CurHeight-1][i] + "+" + Result[CurHeight-1][0] + "<" + Result[CurHeight-1][i] + " ");
                
                if( matr[CurHeight - 1][i] + Result[CurHeight-1][0] < Result[CurHeight-1][i]){
                    Result[i][CurHeight-1] = matr[CurHeight - 1][i] + Result[CurHeight-1][0];
                }
                else{
                    Result[CurHeight-1][i] = Result[CurHeight-1][0];
                }

            }
            CurHeight++; //break; 


        }




        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 6; i++) {
                System.out.print(Result[j][i] + " ");
            }
            System.out.println();
        }

    }
}
