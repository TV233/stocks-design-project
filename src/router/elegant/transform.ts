/* eslint-disable */
/* prettier-ignore */
// Generated by elegant-router
// Read more: https://github.com/soybeanjs/elegant-router

import type { RouteRecordRaw, RouteComponent } from 'vue-router';
import type { AutoRoute } from '@elegant-router/vue';
import type { ElegantRoute } from '@elegant-router/types';

const LAYOUT_PREFIX = 'layout.';
const VIEW_PREFIX = 'view.';

/**
 * transform elegant route to vue route
 * @param routes elegant routes
 * @param layouts layout components
 * @param views view components
 */
export function transformElegantRouteToVueRoute(
  routes: AutoRoute[],
  layouts: Record<string, RouteComponent | (() => Promise<RouteComponent>)>,
  views: Record<string, RouteComponent | (() => Promise<RouteComponent>)>
) {
  const vueRoutes: RouteRecordRaw[] = routes.map(route => {
    const { children, component, ...rest } = route;

    const vueRoute = { ...rest } as RouteRecordRaw;

    if (component) {
      if (isLayout(component)) {
        const layoutName = getLayoutName(component);

        vueRoute.component = layouts[layoutName];
      }

      if (isView(component)) {
        const viewName = getViewName(component);

        vueRoute.component = views[viewName];
      }
    }

    if (children?.length) {
      vueRoute.children = transformElegantRouteToVueRoute(children, layouts, views);
    }

    return vueRoute;
  });

  return vueRoutes;
}

function isLayout(component: string) {
  return component.startsWith(LAYOUT_PREFIX);
}

function getLayoutName(component: string) {
  return component.replace(LAYOUT_PREFIX, '');
}

function isView(component: string) {
  return component.startsWith(VIEW_PREFIX);
}

function getViewName(component: string) {
  return component.replace(VIEW_PREFIX, '');
}

/**
 * transform elegant route to tree route
 * @param routes elegant routes
 */
export function transformElegantRouteToTreeRoute(routes: AutoRoute[]) {
  const treeRoutes = routes.map(route => {
    const { children = [], ...rest } = route;

    const treeRoute: AutoRoute = { ...rest, children: [] };

    const treeMap = new Map<string, AutoRoute>();

    children.forEach(child => {
      treeMap.set(child.name as string, { ...child });
    });

    const treeChildren: AutoRoute[] = [];

    children.forEach(child => {
      const childName = child.name as string;

      // current route level is 2, if has parent, then level is 3 or more
      const hasParent = childName.split('_').length > 2;

      const current = treeMap.get(childName)!;

      if (hasParent) {
        const parentName = childName.split('_').slice(0, -1).join('_');

        const parent = treeMap.get(parentName);

        if (parent) {
          if (!parent.children) {
            parent.children = [];
          }
          parent.children.push(current);
        }
      } else {
        treeChildren.push(current);
      }
    });

    if (treeChildren.length) {
      treeRoute.children = treeChildren;
    }

    return treeRoute;
  });

  return treeRoutes as ElegantRoute[];
}