using IISProject.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Xml;

namespace IISProject.Controllers
{
    public class DriversAPIController : ApiController
    {

        [Route("api/DriversAPI/Driver")]
        public List<Driver> Get()
        {
            return DriversHandler.GetDrivers();
        }

        [Route("api/DriversAPI/Driver/{id}")]
        public Driver Get(string id)
        {
            Driver driver = DriversHandler.GetDrivers().Where(d => d.OIB == id).ToList<Driver>()[0];
            return driver;
        }

        [Route("api/DriversAPI/AddDriver/Object")]
        [HttpPost]
        public List<Driver> Post(Driver driver)
        {
            if (DriversHandler.GetDrivers().Where(d => d.OIB == driver.OIB) != null)
                return null;
            DriversHandler.AppendDriver(driver);
            return DriversHandler.GetDrivers();
        }

        [Route("api/DriversAPI/AddDriver/Feedback")]
        [HttpPost]
        public string PostFeedback(XmlElement driverXml)
        {
            //return driverXml;
            try
            {
                Driver driver = new Driver(driverXml.GetElementsByTagName("FirstName")[0].InnerText,
                driverXml.GetElementsByTagName("LastName")[0].InnerText,
                driverXml.GetElementsByTagName("OIB")[0].InnerText,
                DateTime.Parse(driverXml.GetElementsByTagName("LicenceAcquiredDate")[0].InnerText),
                DateTime.Parse(driverXml.GetElementsByTagName("LicenceExpirationDate")[0].InnerText),
                driverXml.GetElementsByTagName("VehicleCategory")[0].InnerText);
                //return driver;
                return "success";
            }
            catch (Exception e)
            {
                return e.Message + e.StackTrace;
            }
            

            //DriversHandler.AppendDriver(driver);
            //return DriversHandler.GetDrivers();
        }
    }
}
