using Microsoft.ApplicationBlocks.Data;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace Project_1.Models
{
    public class Repo : IRepo
    {
        public Repo() { }

        public DataSet ds { get; set; }
        private string cs = ConfigurationManager.ConnectionStrings["cs"].ConnectionString;

        public City GetCity(int cityID)
        {
            DataRow row = SqlHelper.ExecuteDataset(cs, "GetCity", cityID).Tables[0].Rows[0];
            return new City
            {
                IDCity = (int)row["IDCity"],
                Name = row["Name"].ToString()
            };
        }

        public Role GetRole(int roleID)
        {
            DataRow row = SqlHelper.ExecuteDataset(cs, "GetRole", roleID).Tables[0].Rows[0];
            return new Role
            {
                IDRole = (int)row["IDRole"],
                Name = row["Name"].ToString()
            };
        }

        public Person GetPerson(int personID)
        {
            DataRow row = SqlHelper.ExecuteDataset(cs, "GetPerson", personID).Tables[0].Rows[0];
            return new Person
            {
                IDPerson = (int)row["IDPerson"],
                FName = row["FName"].ToString(),
                LName = row["LName"].ToString(),
                Phone = row["Phone"].ToString(),
                RoleID = (int)row["RoleID"],
                CityID = (int)row["City"],
                Email = row["Email"].ToString(),
                Password = row["Pass"].ToString()
            };
        }

        public IEnumerable<Person> GetAllPeople()
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetAllPeople");

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new Person
                {
                    IDPerson = (int)row["IDPerson"],
                    FName = row["FName"].ToString(),
                    LName = row["LName"].ToString(),
                    Phone = row["Phone"].ToString(),
                    RoleID = (int)row["RoleID"],
                    CityID = (int)row["City"],
                    Email = row["Email"].ToString(),
                    Password = row["Pass"].ToString()
                };
            }
        }

        public IEnumerable<City> GetAllCities()
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetAllCities");

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new City
                {
                    IDCity = (int)row["IDCity"],
                    Name = row["Name"].ToString(),
                };
            }
        }

        public IEnumerable<Role> GetAllRoles()
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetAllRoles");

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new Role
                {
                    IDRole = (int)row["IDRole"],
                    Name = row["Name"].ToString()
                };
            }
        }

        public IEnumerable<string> GetPersonEmails(int personID)
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetAllRoles");

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return row["Address"].ToString();
            }
        }

        public int DeletePerson(int id)
        {
            return SqlHelper.ExecuteNonQuery(cs, "DeletePerson", id);
        }

        public int CreatePerson(Person p)
        {
            return SqlHelper.ExecuteNonQuery(cs, "CreatePerson",
                    p.FName,
                    p.LName,
                    p.Email,
                    p.Phone,
                    p.Password,
                    p.RoleID,
                    p.CityID
            );
        }

        public int UpdatePerson(Person p)
        {
            return SqlHelper.ExecuteNonQuery(cs, "UpdatePerson",
                    p.IDPerson,
                    p.FName,
                    p.LName,
                    p.Email,
                    p.Phone,
                    p.Password,
                    p.RoleID,
                    p.CityID
            );
        }

        public int UpdatePersonGV(Person p)
        {
            return SqlHelper.ExecuteNonQuery(cs, "UpdatePersonGV",
                    p.IDPerson,
                    p.FName,
                    p.LName,
                    p.Email,
                    p.Phone,
                    p.RoleID
            );
        }

        public int CheckLogin(string login, string password)
        {
            DataRow row = SqlHelper.ExecuteDataset(cs, "CheckLogin", login, password).Tables[0].Rows[0];
            return (int)row["Result"];
        }

        public Person GetUserByEmail(string email)
        {
            DataRow row = SqlHelper.ExecuteDataset(cs, "GetUserByEmail", email).Tables[0].Rows[0];
            return new Person
            {
                IDPerson = (int)row["IDPerson"],
                FName = row["FName"].ToString(),
                LName = row["LName"].ToString(),
                Phone = row["Phone"].ToString(),
                RoleID = (int)row["RoleID"],
                CityID = (int)row["City"],
                Email = row["Email"].ToString(),
                Password = row["Pass"].ToString()
            };
        }
    }
}