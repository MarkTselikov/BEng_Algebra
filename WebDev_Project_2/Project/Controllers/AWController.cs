using Project.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Project.Controllers
{
    [Authorize]
    public class AWController : Controller
    {
        public ActionResult DisplayBuyers()
        { 
            return View(Repo.GetBuyers());
        }

        public ActionResult BuyerDetails(int buyerID)
        {
            return View(Repo.GetBuyerBills(buyerID));
        }

        public ActionResult GetCity(int cityID)
        {
            return PartialView("_City", Repo.GetCity(cityID));
        }

        public ActionResult GetProduct(int productID)
        {
            return PartialView("_Product", Repo.GetProduct(productID));
        }

        public ActionResult GetState(int cityID)
        {
            return PartialView("_State", Repo.GetState(cityID));
        }

        public ActionResult GetCommercial(int comID)
        {
            return PartialView("_Commercial", Repo.GetCommercial(comID));
        }

        public ActionResult BillDetails(int billID)
        {
            return View(Repo.GetItemsByBill(billID));
        }

        [HttpGet]
        [Authorize(Roles = "Administrator")]
        public ActionResult EditBuyer(int buyerID)
        {
            ViewBag.ddlCities = Repo.GetCities();
            return View(Repo.GetBuyer(buyerID));
        }

        [HttpPost]
        [Authorize(Roles = "Administrator")]
        public ActionResult EditBuyer(Buyer b)
        {
            if (Repo.UpdateBuyer(b) > 0)
                return RedirectToAction("DisplayBuyers");
            else
                return Content("Something went wrong, it's probaly Russian hackers...");
        }

        [HttpGet]
        [Authorize(Roles = "Administrator")]
        public ActionResult CreateBuyer()
        {
            ViewBag.ddlCities = Repo.GetCities();
            return View();
        }

        [HttpPost]
        [Authorize(Roles = "Administrator")]
        public ActionResult CreateBuyer(Buyer b)
        {
                try
                {
                    Repo.CreateBuyer(b);
                    return RedirectToAction("DisplayBuyers");
                }
                catch (Exception ex)
                {
                    ViewBag.error = ex.GetBaseException().Message;
                    return View("Error");
                }
        }
    }
}