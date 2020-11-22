using Microsoft.ApplicationBlocks.Data;
using Eventager.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Configuration;

namespace Eventager.Models
{
    public class Repo
    {
        public static DataSet ds { get; set; }
        private static string cs = ConfigurationManager.ConnectionStrings["cs"].ConnectionString;

        public static int AddUser(User u)
        {
            return SqlHelper.ExecuteNonQuery(cs, "AddUser",
                    u.FName,
                    u.LName,
                    u.Email,
                    u.Username,
                    u.Password,
                    u.RoleID,
                    u.SubscriptionID
            );
        }

        public static int AddCompany(Company c)
        {
            return SqlHelper.ExecuteNonQuery(cs, "AddCompany",
                    c.IDCompany,
                    c.Name,
                    c.OIB,
                    c.Email,
                    c.Address,
                    c.UserID                   
            );
        }

        public static int AddEvent(Event e)
        {
            return SqlHelper.ExecuteNonQuery(cs, "AddEvent",
                    e.Name,
                    e.Description,
                    e.EventStart,
                    e.EventEnd,
                    1,
                    e.BreakTime,
                    1
            );
        }

        public static int EditUser(User u)
        {
            return SqlHelper.ExecuteNonQuery(cs, "EditUser",
                    u.IDUser,
                    u.FName,
                    u.LName,
                    u.Email,
                    u.Username
            );
        }

        public static int EditEventManager(User u)
        {
            return SqlHelper.ExecuteNonQuery(cs, "EditUser",
                    u.IDUser,
                    u.FName,
                    u.LName,
                    u.Email,
                    u.Username,
                    u.RoleID,
                    u.SubscriptionID
            );
        }

        public static int PaySubscription(int managerID, string creditCardNum, int subscribtionID)
        {
            return SqlHelper.ExecuteNonQuery(cs, "PaySubscription",
                managerID,
                creditCardNum,
                subscribtionID
            );
        }

        public static int PayTickets(int userID, string creditCardNum, int lectureID, int numTickets)
        {
            return SqlHelper.ExecuteNonQuery(cs, "PayTickets",
                userID,
                creditCardNum,
                lectureID,
                numTickets
            );
        }

        public static IEnumerable<Subscription> GetSubscriptions()
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetSubscription");

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new Subscription
                {
                    IDSubscription = (int)row["IDSubscription"],
                    Type = row["Type"].ToString(),
                    Price = (decimal)row["Price"]
                };
            }
        }

        public static IEnumerable<City> GetCities()
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetCities");

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new City
                {
                    IDCity = (int)row["IDCity"],
                    Name = row["Name"].ToString()
                };
            }
        }

        public static IEnumerable<Category> GetCategories()
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetCategory");

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new Category
                {
                    IDCategory = (int)row["IDCategory"],
                    Type = row["Type"].ToString()
                };
            }
        }

        public static Category GetCategory(int categoryID)
        {
            return GetCategories().Single(c => c.IDCategory == categoryID);
        }

        public static IEnumerable<Role> GetRoles()
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetRole");

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new Role
                {
                    IDRole = (int)row["IDRole"],
                    Name = row["Name"].ToString()
                };
            }
        }

        public static IEnumerable<User> GetAllUsers()
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetAllUsers");

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new User
                {
                    IDUser = (int)row["IDUser"],
                    FName = row["Name"].ToString(),
                    LName = row["Surname"].ToString(),
                    Email = row["Email"].ToString(),
                    Username = row["Username"].ToString(),
                    RoleID = (int)row["RoleID"],
                    SubscriptionID = (int)row["SubscriptionID"]
                };
            }
        }

        public static User GetUser(int userID)
        {
            return GetAllUsers().Single(b => b.IDUser == userID);
        }

        public static IEnumerable<Event> GetAllEvents()
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetAllEvents");

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new Event
                {
                    IDEvent = (int)row["IDEvent"],
                    Name = row["Name"].ToString(),
                    Description = row["Description"].ToString(),
                    EventStart = (DateTime)row["EventStart"],
                    EventEnd = (DateTime)row["EventEnd"],
                    CategoryID = (int)row["CategoryID"],
                    BreakTime = (int)row["BreakTime"]
                };
            }
        }

        public static Event GetEvent(int eventID)
        {
            return GetAllEvents().Single(e => e.IDEvent == eventID);
        }

        public static Company GetCompany(int idCompany)
        {
            DataRow row = SqlHelper.ExecuteDataset(cs, "GetCompany", idCompany).Tables[0].Rows[0];
            return new Company
            {
                IDCompany = (int)row["IDGrad"],
                Name = row["Naziv"].ToString(),
                OIB = row["OIB"].ToString(),
                Email = row["Email"].ToString(),
                Address = row["Address"].ToString(),
                UserID = (int)row["UserID"]
            };
        }

        public static IEnumerable<Lecture> GetLecturesPerEvent(int idEvent)
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetLecturesPerEvent", idEvent);

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new Lecture
                {
                    IDLecture = (int)row["IDLecture"],
                    Name = row["Name"].ToString(),
                    Description = row["Description"].ToString(),
                    Start = (DateTime)row["StartTime"],
                    End = (DateTime)row["EndTime"],
                    EventLocationID = (int)row["LocationID"],
                    LecturerID = (int)row["LecturerID"],
                    Hall = row["Hall"].ToString(),
                    Capacity = (int)row["Capacity"],
                    EventID = (int)row["EventID"]
                };
            }
        }

        /*public static IEnumerable<Payment> GetPaymentHistory(int idUser)
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetPaymentHistory", idUser);

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new Payment
                {
                    IDLecture = (int)row["IDUser"],
                    Name = row["Name"].ToString(),
                    Description = row["Description"].ToString(),
                    Start = (DateTime)row["StartTime"],
                    End = (DateTime)row["EndTime"],
                    EventLocationID = (int)row["LocationID"],
                    LecturerID = (int)row["LecturerID"],
                    Hall = row["Hall"].ToString(),
                    Capacity = (int)row["Capacity"],
                    EventID = (int)row["EventID"],
                };
            }
        }*/

        public static IEnumerable<User> GetUsersPerEvent(int idEvent)
        {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "GetUsersPerEvent", idEvent);

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new User
                {
                    IDUser = (int)row["IDUser"],
                    FName = row["Name"].ToString(),
                    LName = row["Surname"].ToString(),
                    Email = row["Email"].ToString(),
                    Username = row["Username"].ToString(),
                    RoleID = (int)row["RoleID"],
                    SubscriptionID = (int)row["SubscriptionID"]
                };
            }
        }

        public static User GetUserByEmail(string email)
        {
            //try
            //{
                DataRow row = SqlHelper.ExecuteDataset(cs, "GetUserByEmail", email).Tables[0].Rows[0];
                return new User
                {
                    IDUser = (int)row["IDUser"],
                    FName = row["Name"].ToString(),
                    LName = row["Surname"].ToString(),
                    Email = row["Email"].ToString(),
                    Username = row["Username"].ToString(),
                    RoleID = (int)row["RoleID"],
                    SubscriptionID = (int)row["SubscriptionID"]
                };
            //}
            //catch { return GetUser(1); }
        }

        public static IEnumerable<Event> EventsCreatedByManager(int id) {
            DataSet ds = SqlHelper.ExecuteDataset(cs, "EventsCreatedByManager", id);

            foreach (DataRow row in ds.Tables[0].Rows)
            {
                yield return new Event
                {
                    IDEvent = (int)row["IDEvent"],
                    Name = row["Name"].ToString(),
                    Description = row["Description"].ToString(),
                    EventStart = (DateTime)row["EventStart"],
                    EventEnd = (DateTime)row["EventEnd"],
                    CategoryID = (int)row["CategoryID"],
                    BreakTime = (int)row["BreakTime"]
                };
            }
        }
    }
}