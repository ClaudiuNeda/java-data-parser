/*
import Patient.Patient;
import Department.Department;
import Stock.Stock;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.annotation.processing.Filer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        List<Stock> titluri = new ArrayList<>();     // citire din CSV

        try (BufferedReader br = new BufferedReader(new FileReader("date/PretVolum.txt"))) {
            br.readLine();
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] p = linie.split(",");
                titluri.add(new Stock(p[0],
                        Double.parseDouble(p[1]),
                        Double.parseDouble(p[2]),
                        Double.parseDouble(p[3]),
                        Double.parseDouble(p[4]),
                        Long.parseLong(p[5])));
            }

        }
        titluri.forEach(System.out::println);


        String url = "jdbc:sqlite:date/Titluri.db";      // citire din SQLite cu jdbc
        Map<String, String> denumiri = new HashMap<>();
        try (Connection con = DriverManager.getConnection(url);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT Simbol, Denumire FROM Titluri")){
            while(rs.next()){
                denumiri.put(rs.getString("Simbol"), rs.getString("Denumire"));
            }

        }
        denumiri.forEach((simbol, denumire) ->
                System.out.println(simbol  + " -> " + denumire));


        Comparator<Stock> dupaValoare =         // afisare valoare min si max, cu valoare = Inchidere * Volum
                            Comparator.comparingDouble(t -> t.getInchidere() * t.getVolum());

        Stock min = titluri.stream().min(dupaValoare).get();
        Stock max = titluri.stream().max(dupaValoare).get();

        System.out.printf("\nTitlul cu valoare minima: ");
        System.out.printf("%-6s %,.2f%n", min.getSimbol(), min.getInchidere() * min.getVolum());
        System.out.printf("\nTitlul cu valoare maxima: ");
        System.out.printf("%-6s %,.2f%n",max.getSimbol(), max.getInchidere() * max.getVolum());


        System.out.println("\nSimbol\t\tDenumire\t\t\t\t\t\tVolum\n");      // afisare obiecte in ordine descrescatoare
        titluri.stream()                                                    // in functie de Volum
                .sorted(Comparator.comparingLong(Stock::getVolum).reversed())
                .forEach(t -> System.out.printf("%-6s\t\t%-25s\t\t%d%n",
                        t.getSimbol(),
                        denumiri.get(t.getSimbol()),
                        t.getVolum()));



        List<Department> sectii = new ArrayList<>();      //   citire din Json pentru Sectii

        try (FileReader fr = new FileReader("date/sectii.json")) {
            JSONArray arr = new JSONArray(new JSONTokener(fr));
            for (int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                sectii.add(new Department(
                        obj.getInt("cod_sectie"),
                        obj.getString("denumire"),
                        obj.getInt("numar_locuri")
                ));
            }
            System.out.println();
        }

        sectii.forEach(System.out::println);



        //   Din Subiect 2, cerința 2: afișează numărul de pacienți pe fiecare secție.

        List<Patient> pacienti = new ArrayList<>();
        String urlSpital = "jdbc:sqlite:date/spital.db";

        try (Connection con = DriverManager.getConnection(urlSpital);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT CNP, NumePacient, VarstaPacient, CodSectie FROM Pacienti")){
            while(rs.next()){
                pacienti.add(new Patient(
                        rs.getLong("CNP"),
                        rs.getString("NumePacient"),
                        rs.getInt("VarstaPacient"),
                        rs.getInt("CodSectie")
                ));
            }
        }

        pacienti.forEach(System.out::println);

        Map<Integer, Long> pacientiPeSectie = pacienti.stream()
                .collect(Collectors.groupingBy(Patient::getCodSectie, Collectors.counting()));

        System.out.println("\nCod Department\t\tDenumire\t\tNr. Pacienti\n");
        sectii.forEach(s -> System.out.printf("%-14d\t%-22s%d%n",
                s.getCodSectie(),
                s.getDenumire(),
                pacientiPeSectie.getOrDefault(s.getCodSectie(), 0L)));


    }
}
*/


import Patient.Patient;
import Department.Department;
import Stock.Stock;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws Exception {

    List<Department> departments = new ArrayList<>();

    try(FileReader fr = new FileReader("date/sectii.json")){
        JSONArray arr = new JSONArray(new JSONTokener(fr));
        for(int i = 0; i < arr.length(); i++){
            JSONObject obj = arr.getJSONObject(i);
            departments.add(new Department(
                    obj.getInt("cod_sectie"),
                    obj.getString("denumire"),
                    obj.getInt("numar_locuri")
            ));
        }
    }

    departments.stream()
            .filter(s -> s.getTotalSeats() > 5)
            .forEach(System.out::println);


        List<Patient> patients = new ArrayList<>();

        String hospitalDbUrl = "jdbc:sqlite:date/spital.db";

        try(Connection con = DriverManager.getConnection(hospitalDbUrl);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT CNP, NumePacient, VarstaPacient, CodSectie FROM Pacienti")){
                while(rs.next()){
                    patients.add(new Patient(
                            rs.getLong("CNP"),
                            rs.getString("NumePacient"),
                            rs.getInt("VarstaPacient"),
                            rs.getInt("CodSectie")
                    ));
                }

            }

        Map<Integer, Long> patientsBySection = patients.stream()
                        .collect(Collectors.groupingBy(Patient::getDepartmentCode, Collectors.counting()));

        System.out.printf("Department Code\t\tDepartment Name\t\tNumber of Patients\n");
        departments.forEach(s -> System.out.printf("%-20d\t%-30s%d%n",
                s.getDepartmentCode(),
                s.getDepartmentName(),
                patientsBySection.getOrDefault(s.getDepartmentCode(), 0L)));


        List<Stock> stocks = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("date/PretVolum.txt"))){
            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String[] p = line.split(",");
                stocks.add(new Stock(p[0],
                        Double.parseDouble(p[1]),
                        Double.parseDouble(p[2]),
                        Double.parseDouble(p[3]),
                        Double.parseDouble(p[4]),
                        Long.parseLong(p[5])
                ));

            }


        }
        System.out.println();
        stocks.forEach(System.out::println);

    }
}





















