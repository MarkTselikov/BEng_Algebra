using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;

namespace Project_1.Models
{
    public class RepoTxt : IRepo
    {
        static int id = 1;

        public int CheckLogin(string login, string password)
        {
            List<Person> people = GetAllPeople() as List<Person>;
            foreach (var p in people)
            {
                if (p.Email == login && p.Password == password)
                    return 1;
            }
            return 0;
        }

        public int CreatePerson(Person p)
        {
            p.IDPerson = id;
            id++;
            using (var tw = new StreamWriter("TextDB/people.txt", true))
            {
                //Environment.NewLine + 
                tw.WriteLine($"{p.IDPerson}|{p.FName}|{p.LName}|{p.Phone}|{p.Email}|{p.Password}|{p.RoleID}|{p.CityID}");
            }

            //File.AppendAllText("TextDB/people.txt",
            //    $"{p.IDPerson}|{p.FName}|{p.LName}|{p.Phone}|{p.Email}|{p.Password}|{p.RoleID}|{p.CityID}" + Environment.NewLine);
            return 1;
        }

        public int DeletePerson(int id)
        {
            List<Person> people = GetAllPeople() as List<Person>;
            foreach (var p in people.ToList())
            {
                if (p.IDPerson == id)
                    people.Remove(p);
            }

            File.WriteAllText("TextDB/people.txt", String.Empty);

            foreach (var p in people.ToList())
            {
                CreatePerson(p);
            }
            return 1;
        }

        public IEnumerable<City> GetAllCities()
        {
            List<City> cities = new List<City>();
            string[] lines = System.IO.File.ReadAllLines("TextDB/cities.txt");

            foreach (var line in lines)
            {
                string[] data = line.Split('|');
                City c = new City
                {
                    IDCity = int.Parse(data[0]),
                    Name = data[1]
                };
                cities.Add(c);
            }

            return cities;
        }

        public IEnumerable<Person> GetAllPeople()
        {
            List<Person> people = new List<Person>();
            string[] lines = System.IO.File.ReadAllLines("TextDB/people.txt");//.Skip(1).ToArray();

            foreach (var line in lines)
            {
                string[] data = line.Split('|');
                Person p = new Person
                {
                    IDPerson = int.Parse(data[0]),
                    FName = data[1],
                    LName = data[2],
                    Phone = data[3],
                    Email = data[4],
                    Password = data[5],
                    RoleID = int.Parse(data[6]),
                    CityID = int.Parse(data[7])
                };
                people.Add(p);
            }

            return people;
        }

        public IEnumerable<Role> GetAllRoles()
        {
            List<Role> roles = new List<Role>();
            string[] lines = System.IO.File.ReadAllLines("TextDB/roles.txt");

            foreach (var line in lines)
            {
                string[] data = line.Split('|');
                Role r = new Role
                {
                    IDRole = int.Parse(data[0]),
                    Name = data[1]
                };
                roles.Add(r);
            }

            return roles;
        }

        public City GetCity(int cityID)
        {
            List<City> cities = GetAllCities() as List<City>;
            foreach (var c in cities)
            {
                if (c.IDCity == cityID)
                    return c;
            }
            return null;
        }

        public Person GetPerson(int personID)
        {
            List<Person> people = GetAllPeople() as List<Person>;
            foreach (var p in people)
            {
                if (p.IDPerson == personID)
                    return p;
            }
            return null;
        }

        public IEnumerable<string> GetPersonEmails(int personID)
        {
            throw new NotImplementedException();
        }

        public Role GetRole(int roleID)
        {
            List<Role> roles = GetAllRoles() as List<Role>;
            foreach (var r in roles)
            {
                if (r.IDRole == roleID)
                    return r;
            }
            return null;
        }

        public Person GetUserByEmail(string email)
        {
            List<Person> people = GetAllPeople() as List<Person>;
            foreach (var p in people)
            {
                if (p.Email == email)
                    return p;
            }
            return null;
        }

        public int UpdatePerson(Person p)
        {
            DeletePerson(p.IDPerson);
            CreatePerson(p);
            return 1;
        }

        public int UpdatePersonGV(Person p)
        {
            Person old = GetPerson(p.IDPerson);
            p.CityID = old.CityID;
            p.Password = old.Password;

            UpdatePerson(p);
            return 1;
        }
    }
}