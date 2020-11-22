using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Project_1.Models
{
    public interface IRepo
    {
        City GetCity(int cityID);
        Role GetRole(int roleID);
        Person GetPerson(int personID);
        IEnumerable<Person> GetAllPeople();
        IEnumerable<City> GetAllCities();
        IEnumerable<Role> GetAllRoles();
        IEnumerable<string> GetPersonEmails(int personID);
        int DeletePerson(int id);
        int CreatePerson(Person p);
        int UpdatePerson(Person p);
        int CheckLogin(string login, string password);
        Person GetUserByEmail(string email);
        int UpdatePersonGV(Person p);
    }
}
