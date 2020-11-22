using Eventager.Models;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Eventager.Controllers
{
    public class EventagerController : Controller
    {
        // GET: Eventager
        public ActionResult DisplayAllEvents()
        {
            try { ViewBag.userRole = Repo.GetUserByEmail(User.Identity.GetUserName()).RoleID; }
            catch { ViewBag.userRole = 2; }
            return View(Repo.GetAllEvents());
        }

        [HttpGet]
        public ActionResult CreateEvent()
        {
            ViewBag.ddlCategory = Repo.GetCategories();
            return View();
        }

        [HttpPost]
        public ActionResult CreateEvent(Event e)
        {
            Repo.AddEvent(e);
            return RedirectToAction("DisplayAllEvents");
        }


        public ActionResult EventDetails(int eventID)
        {
            ViewBag.lectures = Repo.GetLecturesPerEvent(eventID);
            return View(Repo.GetEvent(eventID));
        }


        public ActionResult GetCategory(int categoryID)
        {
            return PartialView("_Category", Repo.GetCategory(categoryID));
        }


        public ActionResult EventManagerDetails(int emID)
        {
            // Add events list to ViewBag for that event manager
            return View(Repo.GetUser(emID));
        }

        [HttpGet]
        public ActionResult RegistrationContinued(string username, string password)
        {
            ViewBag.ddlSubs = Repo.GetSubscriptions();
            ViewBag.ddlRoles = Repo.GetRoles();
            return View();
        }

        [HttpPost]
        public ActionResult RegistrationContinued(User u)
        {
            HttpCookie cookie = new HttpCookie("CurrentUser");
            cookie.Value = u.IDUser.ToString();
            cookie.Expires = DateTime.Now.AddHours(1);
            Request.Cookies.Add(cookie);

            Repo.AddUser(u);
            return RedirectToAction("DisplayAllEvents");
        }


        [HttpGet]
        public ActionResult BuyTicket(int lectureID)
        {
            ViewBag.lectureID = lectureID;
            if (User.Identity.GetUserName() == null || User.Identity.GetUserName() == "")
                return RedirectToAction("Register", "Account");
            ViewBag.userID = Repo.GetUserByEmail(User.Identity.GetUserName()).IDUser;
            return View();
        }


        [HttpPost]
        public ActionResult BuyTicket(LecturePayment l)
        {
            Repo.PayTickets(l.UserID, l.CreditCard, l.LectureID, l.NumOfTickets);
            return RedirectToAction("DisplayAllEvents");
        }

        public ActionResult EventsForManager(int id)
        {
            return View(Repo.EventsCreatedByManager(id));
        }

        public ActionResult UserDetails(string email)
        {
            return View(Repo.GetUserByEmail(User.Identity.GetUserName()));
        }
    }
}