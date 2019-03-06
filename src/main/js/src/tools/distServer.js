const browserSync = require('browser-sync');
const historyApiFallback = require('connect-history-api-fallback');

browserSync({
  port: 4000,
  ui: {
    port: 4001,
  },
  server: {
    baseDir: 'dist',
  },

  files: [
    'src/*.html',
  ],

  middleware: [historyApiFallback()],
});
