import React, { useEffect } from 'react';

const Register = ({ requestRegister, setRegisterError, registerErrorMsg }) => {
  useEffect(
    () => () => {
      setRegisterError('');
    },
    [],
  );

  const handlesubmit = event => {
    event.preventDefault();
    const { registername, registerpass } = event.target;
    if (registername.value.trim().length > 0 && registerpass.value.trim().length > 0) {
      requestRegister({ username: registername.value, password: registerpass.value });
      setRegisterError('');
      event.target.reset();
    } else {
      setRegisterError('Fill out all fields please!');
    }
  };

  return (
    <form className="registerform" onSubmit={handlesubmit}>
      <h2>sign up for free</h2>
      {registerErrorMsg ? <h3>{registerErrorMsg}</h3> : null}
      <div>
        <input className="input" name="registername" type="text" id="registername" />
        <label htmlFor="registername">username</label>
      </div>
      <div>
        <input name="registerpass" type="password" id="registerpass" />
        <label htmlFor="registerpass">password</label>
      </div>
      <button className="button" type="submit">
        <span>register</span>
      </button>
    </form>
  );
};

export default Register;
