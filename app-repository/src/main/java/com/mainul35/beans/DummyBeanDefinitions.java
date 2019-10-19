package com.mainul35.beans;

import org.springframework.stereotype.Component;

@Component
public class DummyBeanDefinitions {
    /*static void main(String[] args) {
        List<Integer> list = [73, 67, 38, 33]
        println gradingStudents(list)
    }

    public static List<Integer> gradingStudents(List<Integer> grades) {
        // Write your code here
        grades.forEach({ i ->
            if (i > 37 ) {
                if (i > 40 && (i + 2) % 5 == 0) {
                    i += 2;
                } else if (i <= 40) {
                    i = 40;
                }
            }
        });
        return grades;
    }*/

    public void print() {
        System.out.println("Printing....");
    }
}
