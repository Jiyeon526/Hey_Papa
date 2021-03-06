const routes = [
  {
    path: "/",
    component: () => import("pages/Login.vue"),
  },
  {
    path: "/signup",
    component: () => import("pages/Signup.vue"),
  },
  {
    path: "/baby",
    component: () => import("pages/Babyinfo.vue"),
  },
  {
    path: "/",
    component: () => import("pages/Login.vue"),
  },
  {
    path: "/signup",
    component: () => import("pages/Signup.vue"),
  },
  {
    path: "/baby",
    component: () => import("pages/Babyinfo.vue"),
  },
  {
    path: "/feed/:article_id",
    name: "feed",
    component: () => import("components/feed/FeedDetail.vue"),
  },
  {
    path: "/quiz/:user_id/:quiz",
    name: "zzimquiz",
    component: () => import("components/quiz/ZzimDetail.vue"),
  },
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "map", component: () => import("pages/Map.vue") },
      { path: "home", component: () => import("pages/Home.vue") },
      { path: "info", component: () => import("pages/Info.vue") },
      { path: "my", component: () => import("pages/My.vue") },
      { path: "set", component: () => import("pages/Setting.vue") },
      { path: "quiz", component: () => import("pages/Quiz.vue") },
      { path: "create", component: () => import("pages/Create.vue") },
      { path: "modify", component: () => import("pages/ModifyArticle.vue") },
      { path: "search", component: () => import("pages/Search.vue") },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/Error404.vue"),
  },
];

export default routes;
