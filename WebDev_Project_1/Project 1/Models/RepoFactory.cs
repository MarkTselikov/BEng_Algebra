using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Project_1.Models
{
    public static class RepoFactory
    {
        public static IRepo GetRepo(string mode)
        {
            if (mode == "txt")
                return new RepoTxt();
            else
                return new Repo();
        }
    }
}