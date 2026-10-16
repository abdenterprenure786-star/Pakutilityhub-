# -*- coding: utf-8 -*-
import os

manifest_content = """{
  "name": "PakUtility Hub — Live Pakistani Rates & Bill Finder",
  "short_name": "PakUtility",
  "description": "Pakistan's verified live utility portal: 24K Gold, Petrol, Diesel, Duplicate Electricity Bills (LESCO, IESCO, KE, MEPCO), 1-100 Storey House Cost, Mandi Bazaar & FBR Salary Tax.",
  "start_url": "./index.html",
  "display": "standalone",
  "orientation": "portrait-primary",
  "background_color": "#060914",
  "theme_color": "#0B1120",
  "lang": "en-PK",
  "icons": [
    {
      "src": "./favicon.png",
      "sizes": "64x64",
      "type": "image/png"
    },
    {
      "src": "./app_logo.png",
      "sizes": "128x128",
      "type": "image/png"
    },
    {
      "src": "./icon-192.png",
      "sizes": "192x192",
      "type": "image/png",
      "purpose": "any maskable"
    },
    {
      "src": "./icon-512.png",
      "sizes": "512x512",
      "type": "image/png",
      "purpose": "any maskable"
    }
  ],
  "categories": ["finance", "utilities", "business"]
}"""

sw_content = """// PakUtility Hub Service Worker - High-Performance Offline Mode (PWA)
const CACHE_NAME = 'pakutility-hub-v4-offline';
const OFFLINE_URL = './index.html';

const ASSETS_TO_CACHE = [
  './',
  './index.html',
  './manifest.json',
  './app_logo.png',
  './app_logo.jpg',
  './favicon.png',
  './icon-192.png',
  './icon-512.png',
  'https://cdnjs.cloudflare.com/ajax/libs/react/18.2.0/umd/react.production.min.js',
  'https://cdnjs.cloudflare.com/ajax/libs/react-dom/18.2.0/umd/react-dom.production.min.js',
  'https://cdnjs.cloudflare.com/ajax/libs/prop-types/15.8.1/prop-types.min.js',
  'https://cdnjs.cloudflare.com/ajax/libs/recharts/2.12.7/Recharts.min.js',
  'https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800;900&family=Noto+Nastaliq+Urdu:wght@400;700&display=swap'
];

// Install Event - Pre-cache core shell & assets
self.addEventListener('install', (event) => {
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      console.log('[ServiceWorker] Pre-caching offline assets');
      return cache.addAll(ASSETS_TO_CACHE).catch((err) => {
        console.warn('[ServiceWorker] Partial pre-cache failure (offline mode still armed)', err);
      });
    }).then(() => self.skipWaiting())
  );
});

// Activate Event - Clean up old cache versions
self.addEventListener('activate', (event) => {
  event.waitUntil(
    caches.keys().then((keyList) => {
      return Promise.all(
        keyList.map((key) => {
          if (key !== CACHE_NAME) {
            console.log('[ServiceWorker] Removing old cache version', key);
            return caches.delete(key);
          }
        })
      );
    }).then(() => self.clients.claim())
  );
});

// Fetch Event - Stale-while-revalidate for local assets, Network-first for external APIs
self.addEventListener('fetch', (event) => {
  const req = event.request;
  if (req.method !== 'GET') return;

  // For HTML navigation requests, use network-first with offline fallback to cached index.html
  if (req.mode === 'navigate') {
    event.respondWith(
      fetch(req).catch(() => {
        return caches.match(OFFLINE_URL).then((res) => {
          if (res) return res;
          return caches.match('./index.html');
        });
      })
    );
    return;
  }

  // Cache-first for images, fonts, scripts
  event.respondWith(
    caches.match(req).then((cachedResponse) => {
      if (cachedResponse) {
        // Fetch fresh copy in background to keep cache up to date
        fetch(req).then((networkResponse) => {
          if (networkResponse && networkResponse.status === 200) {
            caches.open(CACHE_NAME).then((cache) => cache.put(req, networkResponse));
          }
        }).catch(() => {});
        return cachedResponse;
      }
      return fetch(req).then((networkResponse) => {
        if (!networkResponse || networkResponse.status !== 200 || networkResponse.type === 'opaque') {
          return networkResponse;
        }
        const responseToCache = networkResponse.clone();
        caches.open(CACHE_NAME).then((cache) => {
          cache.put(req, responseToCache);
        });
        return networkResponse;
      }).catch(() => {
        // Fallback for images
        if (req.destination === 'image') {
          return caches.match('./app_logo.png');
        }
      });
    })
  );
});
"""

targets = ['.', 'app/applet', 'docs']
for t in targets:
    os.makedirs(t, exist_ok=True)
    with open(os.path.join(t, 'manifest.json'), 'w', encoding='utf-8') as f:
        f.write(manifest_content)
    with open(os.path.join(t, 'sw.js'), 'w', encoding='utf-8') as f:
        f.write(sw_content)

print("Offline manifest.json and sw.js generated across all destinations.")
