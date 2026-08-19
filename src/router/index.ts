import { createRouter, createWebHistory } from '@ionic/vue-router';
import type { RouteRecordRaw } from 'vue-router';

const isAuthenticated = () => localStorage.getItem('galeria-session') === 'true';
const routes: RouteRecordRaw[] = [
  { path: '/', redirect: () => (isAuthenticated() ? '/home' : '/login') },
  { path: '/login', component: () => import('@/views/LoginPage.vue') },
  { path: '/cadastro', component: () => import('@/views/RegisterPage.vue') },
  { path: '/home', component: () => import('@/views/HomePage.vue'), meta: { requiresAuth: true } },
  { path: '/sobre', component: () => import('@/views/AboutPage.vue'), meta: { requiresAuth: true } },
  { path: '/:pathMatch(.*)*', redirect: '/' }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

router.beforeEach((to) => {
  if (to.meta.requiresAuth && !isAuthenticated()) return '/login';
  if ((to.path === '/login' || to.path === '/cadastro') && isAuthenticated()) return '/home';
  return true;
});

export default router
