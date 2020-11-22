using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace IISProject.Models
{
    public static class DriversHandler
    {
        private static bool initialized = false;
        private static List<Driver> drivers;

        private static void init()
        {
            drivers = new List<Driver>();

            drivers.Add(new Driver("Benadryl", "Cuttlefish", "11111111111",
                new DateTime(2017, 2, 1), new DateTime(2018, 2, 1), "Light Car"));
            drivers.Add(new Driver("Billiardball", "Chunkybap", "22222222222",
                new DateTime(2017, 2, 1), new DateTime(2018, 2, 1), "Heavy Car"));
            drivers.Add(new Driver("Bonapard", "Bandrsnatch", "33333333333",
                new DateTime(2017, 2, 1), new DateTime(2018, 2, 1), "APC"));
            drivers.Add(new Driver("Backitup", "Cupboardlatch", "44444444444",
                new DateTime(2017, 2, 1), new DateTime(2018, 2, 1), "Falcon 9 Rocket"));
            drivers.Add(new Driver("Baseballmit", "Covertrack", "12345678901",
                new DateTime(2017, 2, 1), new DateTime(2018, 2, 1), "Motorcycle"));

            initialized = true;
        }

        public static List<Driver> GetDrivers()
        {
            if (!initialized)
                init();

            return drivers;
        }

        public static void AppendDriver(Driver d)
        {
            if (!initialized)
                init();
            drivers.Add(d);
        }
    }
}