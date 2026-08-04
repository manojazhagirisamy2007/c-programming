using System;

interface IStudent
{
    void GetData();
    void DisplayData();
}

class Student : IStudent
{
    int id;
    string name;

    public void GetData()
    {
        Console.Write("Enter Student ID: ");
        id = Convert.ToInt32(Console.ReadLine());

        Console.Write("Enter Student Name: ");
        name = Console.ReadLine();
    }

    public void DisplayData()
    {
        Console.WriteLine("\nStudent Details");
        Console.WriteLine("---------------");
        Console.WriteLine("ID   : " + id);
        Console.WriteLine("Name : " + name);
    }
}

class Program
{
    static void Main(string[] args)
    {
        Student s = new Student();
        s.GetData();
        s.DisplayData();

        Console.ReadKey();
    }
}