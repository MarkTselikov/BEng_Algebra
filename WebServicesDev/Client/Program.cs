using IISProject.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Serialization;

namespace Client
{
    class Program
    {
        static void Main(string[] args)
        {
            ShowMenu();
        }


        static void ShowMenu()
        {
            int choice = -1;
            while (choice != 0)
            {
                Console.WriteLine("Please select the option from the menu: ");
                Console.WriteLine("\t1. Get all the drivers");
                Console.WriteLine("\t2. Get a driver by OIB");
                Console.WriteLine("\t3. Insert new driver");
                Console.WriteLine("\t0. Exit");

                choice = Int32.Parse(Console.ReadLine());
                Console.WriteLine();
                switch (choice)
                {
                    case 1:
                        GetDrivers();
                        break;
                    case 2:
                        GetOneDriver();
                        break;
                    case 3:
                        InsertDriver();
                        break;
                    default:
                        break;
                }
                Console.WriteLine("\n");
            }
        }

        private static void GetDrivers()
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create("http://localhost:50000/api/DriversAPI/Driver");
            request.Method = "GET";
            request.Accept = "application/xml";
            request.ContentType = "application/xml";

            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            Stream dataResponse = response.GetResponseStream();

            XmlDocument doc = new XmlDocument();
            doc.Load(dataResponse);
            doc.Save(Console.Out);
        }

        private static void InsertDriver()
        {
            Driver driver = CreateDriver();

            StringWriter writer = new StringWriter();
            XmlSerializer xmlTransformer = new XmlSerializer(driver.GetType());
            xmlTransformer.Serialize(writer, driver);

            string xml = writer.ToString();
            xml = xml.Replace("<?xml version=\"1.0\" encoding=\"utf-16\"?>",
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>");

            xml = xml.Replace("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"",
                "xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://schemas.datacontract.org/2004/07/IISProject.Models\"");

            byte[] data = Encoding.UTF8.GetBytes(xml);
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create("http://localhost:50000/api/DriversAPI/AddDriver/Object");
            request.Method = "POST";
            request.Accept = "application/xml";
            request.ContentType = "text/xml; encoding='utf-8'";

            Stream dataRequest = request.GetRequestStream();
            dataRequest.Write(data, 0, data.Length);
            dataRequest.Close();

            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            Stream dataResponse = response.GetResponseStream();

            XmlDocument doc = new XmlDocument();

            if (doc.SelectSingleNode("//Driver") == null)
                Console.WriteLine("\nDriver with such OIB already exists\n");
            else
            {
                doc.Load(dataResponse);
                doc.Save(Console.Out);
            }
                
        }

        private static Driver CreateDriver()
        {
            Console.WriteLine("Enter first name: ");
            string fName = Console.ReadLine();

            Console.WriteLine("Enter last name: ");
            string lName = Console.ReadLine();

            Console.WriteLine("Enter OIB: ");
            string oib = Console.ReadLine();

            Console.WriteLine("Enter driver license acquiring date: (yyyy/mm/dd)");
            string[] dateRaw = Console.ReadLine().Split('/');
            DateTime licAcq = new DateTime(int.Parse(dateRaw[0]), int.Parse(dateRaw[1]), int.Parse(dateRaw[2]));

            Console.WriteLine("Enter driver license expiration date: (yyyy/mm/dd)");
            dateRaw = Console.ReadLine().Split('/');
            DateTime licExp = new DateTime(int.Parse(dateRaw[0]), int.Parse(dateRaw[1]), int.Parse(dateRaw[2]));

            Console.WriteLine("Enter vehicle category: ");
            string vehCat = Console.ReadLine();

            return new Driver(fName, lName, oib, licAcq, licExp, vehCat);
        }

        private static void GetOneDriver()
        {
            Console.WriteLine("Insert the OIB: ");
            String oib = Console.ReadLine();
            Console.WriteLine();

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create($"http://localhost:50000/api/DriversAPI/Driver/{oib}");
            request.Method = "GET";
            request.Accept = "application/xml";
            request.ContentType = "application/xml";

            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            Stream dataResponse = response.GetResponseStream();

            XmlDocument doc = new XmlDocument();
            doc.Load(dataResponse);
            doc.Save(Console.Out);
        }
    }
}
