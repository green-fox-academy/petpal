import React, { useEffect } from 'react';

const Login = ({ requestLogin, loginErrorMsg, setLoginError }) => {
  useEffect(
    () => () => {
      setLoginError('');
    },
    [],
  );

  const handleSubmit = event => {
    event.preventDefault();
    const { loginname, loginpass } = event.target;
    if (loginname.value.trim().length > 0 && loginpass.value.trim().length > 0) {
      requestLogin({ username: loginname.value, password: loginpass.value });
      event.target.reset();
      setLoginError('');
    } else {
      setLoginError('Fill out all fields please!');
    }
  };

  return (
    <form className="loginform" onSubmit={handleSubmit}>
      <h2>log in to your account</h2>
      {loginErrorMsg !== '' ? <h3>{loginErrorMsg}</h3> : null}
      <div>
        <input className="input" name="loginname" type="text" id="loginname" />
        <label htmlFor="loginname">username</label>
      </div>
      <div>
        <input name="loginpass" type="password" id="loginpass" />
        <label htmlFor="loginpass">password</label>
      </div>
      <button className="button" type="submit">
        <span>sign me in</span>
      </button>
    </form>
  );
};

export default Login;
