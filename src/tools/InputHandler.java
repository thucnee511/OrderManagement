/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class InputHandler {

    public static Scanner sc = new Scanner(System.in);

    public static int getInt(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                int inputNum = Integer.parseInt(sc.nextLine());
                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static int getInt(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        while (true) {
            try {
                System.out.print(inputMsg);
                int inputNum = Integer.parseInt(sc.nextLine());
                if (lowerBound > upperBound) {
                    upperBound = upperBound + lowerBound - (lowerBound = upperBound);
                }
                if (inputNum < lowerBound || inputNum > upperBound) {
                    throw new Exception();
                }
                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            } catch (Exception e) {
                String msg = String.format("Must be in [%d,%d]", lowerBound, upperBound);
                System.out.println(msg);
            }
        }
    }

    public static int getPositiveInt(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                int inputNum = Integer.parseInt(sc.nextLine());
                if (inputNum <= 0) {
                    throw new Exception();
                }
                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            } catch (Exception e) {
                System.out.println("Must be greater than 0!");
            }
        }
    }

    public static double getPositiveReal(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                double inputNum = Double.parseDouble(sc.nextLine());
                if (inputNum <= 0) {
                    throw new Exception();
                }
                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            } catch (Exception e) {
                System.out.println("Must be greater than 0");
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                if (inputStr.isBlank()) {
                    throw new Exception();
                }
                return inputStr;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getString(String inputMsg) {
        System.out.print(inputMsg);
        return sc.nextLine();
    }

    public static String getString(String inputMsg, String errorMsg, String regex) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                if (!inputStr.matches(regex)) {
                    throw new Exception();
                }
                return inputStr;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg, int minLength, int maxLength) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                int strLen = inputStr.length();
                if (strLen < minLength || strLen > maxLength) {
                    throw new Exception();
                }
                return inputStr;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getDateDMY(String inputMsg) {
        String regex = "\\d{2}/\\d{2}\\d{4}";
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                if (!inputStr.matches(regex)) {
                    throw new Exception();
                }
                String date[] = inputStr.split("/");
                int day = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);
                if (checkValidDate(day, month, year)) {
                    return inputStr;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid date: dd/mm/yyyy");
            }
        }
    }

    public static String getDateMDY(String inputMsg) {
        String regex = "\\d{2}/\\d{2}\\d{4}";
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                if (!inputStr.matches(regex)) {
                    throw new Exception();
                }
                String date[] = inputStr.split("/");
                int month = Integer.parseInt(date[0]);
                int day = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);
                if (checkValidDate(day, month, year)) {
                    return inputStr;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid date: mm/dd/yyyy");
            }
        }
    }
    
    public static boolean getBoolean(String inputMsg){
        while (true) {
            try {
                System.out.print(inputMsg);
                boolean inputBool = Boolean.parseBoolean(sc.nextLine());
                return inputBool;
            } catch (Exception e) {
                System.out.println("True or False!!");
            }
        }
    }

    private static boolean checkValidDate(int day, int month, int year) {
        boolean leap = false;
        if (year < 0) {
            return false;
        }
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            leap = true;
        }
        if (month == 2) {
            if (leap) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        } else if (month < 8) {
            if (month % 2 == 1) {
                return day <= 31;
            } else {
                return day <= 30;
            }
        } else if (month <= 12) {
            if (month % 2 == 0) {
                return day <= 31;
            } else {
                return day <= 30;
            }
        } else {
            return false;
        }
    }
}
