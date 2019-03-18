const common = require('./webpack.config.common');
const merge = require('webpack-merge');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = merge(common, {
  mode: 'development',
  devServer: {
    contentBase: './dist',
    historyApiFallback: true,
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: 'src/index.html',
      favicon: 'src/assets/icons/favicon.ico',
      minify: {
        removeComments: true,
        collapseWhitespace: true,
      },
    }),
  ],
});
