const webpack = require('webpack');
const chalk = require('chalk');
const config = require('../../webpack.config.prod');

webpack(config).run((error, stats) => {
  if (error) { // so a fatal error occurred. Stop here.
    console.log(chalk.red(error)); // eslint-disable-line
    return 1;
  }

  const jsonStats = stats.toJson();

  if (jsonStats.hasErrors) {
    return jsonStats.errors.map(err => console.log(chalk.red(err))); // eslint-disable-line
  }

  if (jsonStats.hasWarnings) {
    console.log('Webpack generated the following warnings: '); // eslint-disable-line
    jsonStats.warnings.map(warning => console.log(chalk.yellow(warning))); // eslint-disable-line
  }

  console.log(`Webpack stats: ${stats}`); // eslint-disable-line

  // if we got this far, the build succeeded.
  console.log(chalk.blue('Your app is compiled in production mode in /dist. It\'s ready to roll!')); // eslint-disable-line

  return 0;
});
